package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

data class Trains(

        @JsonProperty("trains")
        val trains: List<Train>
) {
    data class Train(

            // TODO Depend on JDregistry (client-data) for proper data types
            val namespace: String,
            val name: String,
            val last_updated: ZonedDateTime
    )
}
