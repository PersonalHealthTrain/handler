package de.difuture.ekut.pht.project.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "RailedTrain")
data class RailedTrain(

        @Id @GeneratedValue
        val id: Int,

        @ManyToMany(cascade = [ CascadeType.ALL ] )
        @JoinTable(
                name = "TrainOnRails_Stations",
                joinColumns = [ JoinColumn(name = "trainOnRails_id") ] ,
                inverseJoinColumns = [ JoinColumn(name = "station_id") ])
        val route: List<Station>,

        /**
         * Index of the Station where the train is currently located.
         * -1 means that train has not yet been pushed to the registry
         */
        val currentStation: Int = -1
)
