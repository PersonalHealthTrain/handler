package de.difuture.ekut.pht.project.repository

import de.difuture.ekut.pht.project.domain.ApprovalState
import org.springframework.data.jpa.repository.JpaRepository

interface ApprovalStateRepository : JpaRepository<ApprovalState, Int>{

    fun findByProjectIdAndStationId(projectId: Int, stationId: Int) : ApprovalState?
}
