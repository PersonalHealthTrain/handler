package de.difuture.ekut.pht.project.controller

import de.difuture.ekut.pht.project.api.ProjectSubmission
import de.difuture.ekut.pht.project.service.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/project")
@CrossOrigin
class ProjectController
@Autowired constructor(private  val service: ProjectService) {

    @PostMapping
    fun create(@RequestBody submission: ProjectSubmission) = this.service.submit(submission)
}
