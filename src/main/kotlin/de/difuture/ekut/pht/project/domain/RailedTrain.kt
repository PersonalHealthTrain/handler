package de.difuture.ekut.pht.project.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import de.difuture.ekut.pht.lib.data.TrainName
import java.time.Instant
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "RailedTrain")
@JsonIgnoreProperties("project")
data class RailedTrain(

        @Id @GeneratedValue
        val id: Int,

        @Convert(converter = TrainNameConverter::class)
        val train: TrainName,

        @Convert(converter = RouteConverter::class)
        val route: List<Int>,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "project_id")
        @JsonIgnore
        val project: Project,

        /**
         * Index of the Station where the train is currently located.
         * -1 means that train has not yet been pushed to the registry
         */
        val currentStation: Int = -1
) {
    val createdAt = Instant.now()
    override fun toString() = "RailedTrain[id=$id,route=$route,currentStation=$currentStation]"
    override fun equals(other: Any?) = other is RailedTrain && other.id == this.id
    override fun hashCode() = this.id.hashCode()
}
