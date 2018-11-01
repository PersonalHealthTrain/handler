package de.difuture.ekut.pht.project.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Station")
data class Station(

        @Id @GeneratedValue
        val id: Int,
        val name: String

//    @ManyToMany(cascade = [ CascadeType.ALL ] )
//    @JoinTable(
//            name = "Project_Station",
//            joinColumns = [ JoinColumn(name = "project_id") ] ,
//            inverseJoinColumns = [ JoinColumn(name = "station_id") ])
//    private val stations: MutableSet<Project> = HashSet()
)
