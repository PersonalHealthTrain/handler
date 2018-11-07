package de.difuture.ekut.pht.project.service

import com.spotify.docker.client.DefaultDockerClient
import de.difuture.ekut.pht.project.domain.RailedTrain
import de.difuture.ekut.pht.project.props.ProjectRegistryProperties
import de.difuture.ekut.pht.project.repository.RailedTrainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path


@Service
class RailedTrainService
@Autowired constructor(
        private val repo: RailedTrainRepository,
        private val props: ProjectRegistryProperties) {

    // TODO Move to persiance
    private val stagedTrains = HashSet<RailedTrain>()

    fun findById(id: Int) : RailedTrain? = repo.findById(id).orElse(null)

    private val docker = DefaultDockerClient.fromEnv().build()

    /**
     * Scans for prepared trains, adds the train route and pushes the train to the reistry
     */
    @Scheduled(fixedDelay = 2000)
    fun stageTrains() {

        val nextTrain = this.repo.getAllByCurrentStation(-1).firstOrNull()
        if (nextTrain != null && nextTrain !in stagedTrains) {
            println(nextTrain)
            this.stagedTrains.add(nextTrain)
            this.importRouteAndPushtoRegisty(nextTrain)
        }
    }

    // TODO Currently the route needs to be imported like this.
    // TODO Later, we would extend the TrainAPI to allow setting the route
    // TODO via the command interface
    private fun importRouteAndPushtoRegisty(railedTrain: RailedTrain) {

        val project = railedTrain.project
        fun createBuildContext() : Path {
            val tempDirWithPrefix = Files.createTempDirectory(project.id.toString())

            // Write routeFile and DockerFile
            this.writeDockerfile(
                    tempDirWithPrefix.resolve("Dockerfile"), project.train)

            // Do not write the first station to the route file, since this will be used for tagging the initial image
            this.writeRouteFile(
                    tempDirWithPrefix.resolve("route"), railedTrain.route.split("-").map { it.toInt() }.drop(1))

            this.writeRailsFile(
                    tempDirWithPrefix.resolve("rail"), railedTrain.id)

            return tempDirWithPrefix.toAbsolutePath()
        }
        val buildContext = createBuildContext()

        // Build the new image from the Build Context with the train registry as target
        val firstStop = railedTrain.route.split('-').first()

        val hostname = props.uri.host
        val port = props.uri.port.let { if (it == -1) "" else ":$it"}

        val repoName = "$hostname$port/namespace/${project.train}:${railedTrain.id}-station.$firstStop"
        docker.build(buildContext, repoName)
        docker.push(repoName)
    }

    private fun writeRouteFile(file: Path, route : List<Int>) {

        Files.write(file, route.map { it.toString() })
    }
    private fun writeDockerfile(file: Path, train: String) {

        Files.write(file, listOf(
                "FROM personalhealthtrain/%s:base".format(train),
                "COPY route /opt/train/route",
                "COPY rail /opt/train/rail"))
    }

    private fun writeRailsFile(file: Path, railID: Int) {
        Files.write(file, listOf(railID.toString()) )
    }
}
