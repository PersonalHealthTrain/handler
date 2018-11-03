package de.difuture.ekut.pht.project.service

import com.fasterxml.jackson.databind.JsonNode
import de.difuture.ekut.pht.project.api.Trains
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.time.ZonedDateTime

/**
 * Service responsible for fetching available trains from Docker Hub
 */
@Service
class TrainService {

    private val template = RestTemplate()
    private val uri = URI.create("https://registry.hub.docker.com/v2/repositories/personalhealthtrain/")

    private val JsonNode.name
    get() = this["name"].asText()

    private val JsonNode.namespace
    get() = this["namespace"].asText()

    private val JsonNode.lastUpdated
    get() = ZonedDateTime.parse(this["last_updated"].asText())

    /**
     * Returns the trains from Docker Hub
     */
    fun getTrains() = Trains(
        template.getForEntity(this.uri, JsonNode::class.java).body!!["results"]
                .filter { it.name.startsWith("train_") }
                .map {
            Trains.Train(
                    namespace = it.namespace,
                    name = it.name,
                    last_updated = it.lastUpdated
            )
        }
    )
}
