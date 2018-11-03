package de.difuture.ekut.pht.project.service

import de.difuture.ekut.pht.project.api.Approval
import de.difuture.ekut.pht.project.domain.Project
import de.difuture.ekut.pht.project.repository.ProjectRepository
import de.difuture.ekut.pht.project.repository.StationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ApprovalService
@Autowired constructor(
        private val projectRepo : ProjectRepository,
        private val stationRepo : StationRepository) {

    fun update(approval: Approval) : Project? {

        val opt1 = this.projectRepo.findById(approval.projectId)
        val opt2 = this.stationRepo.findById(approval.stationId)
        if (opt1.isPresent && opt2.isPresent) {

            val station = opt2.get()
            val oldProject = opt1.get()
            val project = opt1.get().copy(
                    pendingBy = oldProject.pendingBy.minus(station),
                    approvedBy = oldProject.approvedBy.minus(station),
                    rejectedBy = oldProject.rejectedBy.minus(station))

            return this.projectRepo.save(
                    if (approval.approved)
                        project.copy(approvedBy = project.approvedBy.plus(station))
                    else
                        project.copy(rejectedBy = project.rejectedBy.plus(station))
            )
        }
        return null
    }
}
