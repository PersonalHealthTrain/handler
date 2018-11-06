package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty

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
        val projectId: Int
)
