package de.difuture.ekut.pht.project.service

import de.difuture.ekut.pht.project.api.ProjectSubmission
import de.difuture.ekut.pht.project.api.TrainSubmission
import de.difuture.ekut.pht.project.domain.Project
import de.difuture.ekut.pht.project.domain.RailedTrain
import de.difuture.ekut.pht.project.domain.Station
import de.difuture.ekut.pht.project.repository.ProjectRepository
import de.difuture.ekut.pht.project.repository.StationRepository
import de.difuture.ekut.pht.project.repository.RailedTrainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService
@Autowired constructor(
        private val projectRepo: ProjectRepository,
        private val stationRepo: StationRepository,
        private val trainOnRailsRepo: RailedTrainRepository) {

    /**
     * Creates a new Project from the provided [ProjectSubmission]
     *
     * @param submission The [ProjectSubmission] from which a new project should be generated
     * @return [Project] The newly generated project
     */
    fun submit(submission: ProjectSubmission) : Project =
            stationRepo.findAll().let { concernedStations ->
                this.projectRepo.save(Project(
                        0,
                        submission.title,
                        submission.description,
                        submission.email,
                        submission.trains,
                        concernedStations.toSet()))
            }

    fun findAll(): List<Project> = projectRepo.findAll()

    fun findById(id: Int): Project? = projectRepo.findById(id).orElse(null)

    fun submitTrain(submission: TrainSubmission): RailedTrain? {

        val project: Project = this.projectRepo.findById(submission.projectId).orElse(null) ?: return null
        return trainOnRailsRepo.save(
                RailedTrain(
                        0,
                        submission.train,
                        submission.route,
                        project))
    }
}
