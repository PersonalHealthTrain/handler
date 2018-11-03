package de.difuture.ekut.pht.project.domain

import java.net.URI
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Station")
data class Station(

        @Id @GeneratedValue
        val id: Int,
        val name: String,
        val uri: URI? = null
)
