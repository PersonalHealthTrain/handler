package de.difuture.ekut.pht.project.domain

import de.difuture.ekut.pht.lib.data.TrainName
import org.hibernate.annotations.CollectionType
import javax.persistence.CascadeType
import javax.persistence.CollectionTable
import javax.persistence.Convert
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "Project")
data class Project(

    /*
     *  Project Information
     */
    @Id @GeneratedValue
    val id: Int,
    val title: String,
    val description: String,
    val email: String,

    @Convert(converter = TrainNameSetConverter::class)
    val trains: Set<TrainName>,

    /*
     *  Station relations
     */
    @ManyToMany(cascade = [ CascadeType.ALL ], fetch = FetchType.EAGER )
    @JoinTable(
            name = "Project_Pending",
            joinColumns = [ JoinColumn(name = "project_id") ] ,
            inverseJoinColumns = [ JoinColumn(name = "station_id") ])
    val pendingBy: Set<Station>,

    @ManyToMany(cascade = [ CascadeType.ALL ], fetch = FetchType.EAGER )
    @JoinTable(
            name = "Project_Approved",
            joinColumns = [ JoinColumn(name = "project_id") ] ,
            inverseJoinColumns = [ JoinColumn(name = "station_id") ])
    val approvedBy: Set<Station> = emptySet(),

    @ManyToMany(cascade = [ CascadeType.ALL ], fetch = FetchType.EAGER )
    @JoinTable(
            name = "Project_Rejected",
            joinColumns = [ JoinColumn(name = "project_id") ] ,
            inverseJoinColumns = [ JoinColumn(name = "station_id") ])
    val rejectedBy: Set<Station> = emptySet(),

    // Trains that are railed for this project
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = [ CascadeType.ALL ],
            mappedBy = "project")
    val railedTrains: Set<RailedTrain> = emptySet()
)
