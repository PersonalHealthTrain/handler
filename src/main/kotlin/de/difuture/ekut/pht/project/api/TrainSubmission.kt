package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.data.TrainName

data class TrainSubmission(

        /**
         * The route as a list of stations
         */
        @JsonProperty("route")
        val route: List<Int>,

        /**
         * Which project this train submission refers to
         */
        @JsonProperty("projectId")
        val projectId: Int,

        /**
         * The Train that should be submitted to the project
         */
        @JsonProperty("train")
        val train: TrainName
)
