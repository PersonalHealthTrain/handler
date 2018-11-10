package de.difuture.ekut.pht.project.api

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.data.TrainName

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
         * PI's email
         */
        @JsonProperty("email")
        val email: String,

        /**
         * The Trains that the Project wants to use
         */
        @JsonProperty("trains")
        val trains: Set<TrainName>
)
