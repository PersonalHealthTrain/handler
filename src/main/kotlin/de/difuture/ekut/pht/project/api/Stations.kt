package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.project.domain.Station

data class Stations(

        @JsonProperty("stations")
        val stations: List<Station>
)
