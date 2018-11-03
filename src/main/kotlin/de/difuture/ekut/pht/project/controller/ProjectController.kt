package de.difuture.ekut.pht.project.controller

import de.difuture.ekut.pht.project.api.ProjectSubmission
import de.difuture.ekut.pht.project.api.Projects
import de.difuture.ekut.pht.project.domain.Project
import de.difuture.ekut.pht.project.service.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
@CrossOrigin
class ProjectController
@Autowired constructor(private val service: ProjectService) {

    private val NOT_FOUND: ResponseEntity<Project> = ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody submission: ProjectSubmission) = this.service.submit(submission)

    @GetMapping
    fun getAll(): Projects = Projects(service.findAll())

    @GetMapping
    @RequestMapping("/{id}")
    fun get(@PathVariable("id") id: Int): ResponseEntity<Project> =
            this.service.findById(id)?.let { ResponseEntity.ok(it) } ?: NOT_FOUND
}

