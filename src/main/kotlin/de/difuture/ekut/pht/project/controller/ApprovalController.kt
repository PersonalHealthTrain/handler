package de.difuture.ekut.pht.project.controller

import de.difuture.ekut.pht.project.api.Approval
import de.difuture.ekut.pht.project.service.ApprovalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/approvals")
@CrossOrigin
class ApprovalController
@Autowired constructor(private val service: ApprovalService) {

    @PostMapping
    fun update(@RequestBody approval: Approval) = service.update(approval)
}
