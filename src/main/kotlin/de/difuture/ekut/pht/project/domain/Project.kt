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
@Table(name = "Project")
data class Project(

    @Id @GeneratedValue
    val id: Int,
    val title: String,
    val description: String,
    val train: String,

    @ManyToMany(cascade = [ CascadeType.ALL ] )
    @JoinTable(
            name = "Project_Pending",
            joinColumns = [ JoinColumn(name = "project_id") ] ,
            inverseJoinColumns = [ JoinColumn(name = "station_id") ])
    val pendingBy: Set<Station>,

    @ManyToMany(cascade = [ CascadeType.ALL ] )
    @JoinTable(
            name = "Project_Approved",
            joinColumns = [ JoinColumn(name = "project_id") ] ,
            inverseJoinColumns = [ JoinColumn(name = "station_id") ])
    val approvedBy: Set<Station> = emptySet(),


    @ManyToMany(cascade = [ CascadeType.ALL ] )
    @JoinTable(
            name = "Project_Rejected",
            joinColumns = [ JoinColumn(name = "project_id") ] ,
            inverseJoinColumns = [ JoinColumn(name = "station_id") ])
    val rejectedBy: Set<Station> = emptySet()
)
