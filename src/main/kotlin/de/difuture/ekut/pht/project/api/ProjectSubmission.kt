package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Represents a Project Submission
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
data class ProjectSubmission(

        /**
         * The title of the project
         */
        @JsonProperty("title")
        val title: String,

        /**
         * The Description of the Project
         */
        @JsonProperty("description")
        val description: String,

        /**
         * The Stations that the Project targets
         */
        @JsonProperty("stations")
        val stations: Set<Int>
)
