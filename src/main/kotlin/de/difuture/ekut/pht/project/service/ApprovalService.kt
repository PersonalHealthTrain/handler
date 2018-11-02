package de.difuture.ekut.pht.project.service

import de.difuture.ekut.pht.project.repository.ApprovalStateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ApprovalService
@Autowired constructor(private val repo: ApprovalStateRepository) {

    private fun update(projectId: Int, stationId: Int) = repo.findByProjectIdAndStationId(projectId, stationId)


}