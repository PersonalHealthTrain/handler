package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty

data class Approval(

    @JsonProperty("projectId")
    val projectId: Int,

    @JsonProperty("stationId")
    val stationId: Int,

    @JsonProperty("approved")
    val approved: Boolean
)
