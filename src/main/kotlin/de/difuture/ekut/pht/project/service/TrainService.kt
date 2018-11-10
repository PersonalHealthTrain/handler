package de.difuture.ekut.pht.project.service

import com.fasterxml.jackson.databind.JsonNode
import de.difuture.ekut.pht.project.api.Trains
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.time.ZonedDateTime

/**
 * Service responsible for fetching available dockerHubTrains from Docker Hub
 */
@Service
class TrainService {

    private val template = RestTemplate()
    private val uri = URI.create("https://registry.hub.docker.com/v2/repositories/personalhealthtrain/")

    private fun JsonNode.name() = this["name"].asText()

    private fun JsonNode.asDockerHubTrain() =
           Trains.DockerHubTrain(
                namespace = this["namespace"].asText(),
                name = this.name(),
                last_updated = ZonedDateTime.parse(this["last_updated"].asText())
            )


    /**
     * Returns the dockerHubTrains from Docker Hub
     */
    fun getTrains() = Trains(
        template.getForEntity(this.uri, JsonNode::class.java).body!!["results"]
                .filter { it.name().startsWith("train_") }
                .map { it.asDockerHubTrain() }
    )
}
