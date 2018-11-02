package de.difuture.ekut.pht.project.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name = "ApprovalState")
data class ApprovalState(

        @Id @GeneratedValue
        val id: Int,

        @OneToOne
        @JoinColumn(name = "station_id")
        val station: Station,

        @OneToOne
        @JoinColumn(name = "project_id")
        val project: Project,

        /**
         * Whether this ApprovalUpdate State is Accepted or Rejected
         */
        val approved: Boolean
)
