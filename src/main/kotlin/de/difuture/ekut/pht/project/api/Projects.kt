package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.project.domain.Project

data class Projects(

        @JsonProperty("projects")
        val projects: List<Project>
)
