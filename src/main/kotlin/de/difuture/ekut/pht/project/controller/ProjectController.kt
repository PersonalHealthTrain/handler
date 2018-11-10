package de.difuture.ekut.pht.project.controller

import de.difuture.ekut.pht.project.api.ProjectSubmission
import de.difuture.ekut.pht.project.api.Projects
import de.difuture.ekut.pht.project.api.TrainSubmission
import de.difuture.ekut.pht.project.domain.Project
import de.difuture.ekut.pht.project.domain.RailedTrain
import de.difuture.ekut.pht.project.service.ProjectService
import de.difuture.ekut.pht.project.service.RailedTrainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
@CrossOrigin
class ProjectController
@Autowired constructor(
        private val projectService: ProjectService,
        private val railedTrainService: RailedTrainService) {

    private fun <T> T?.orNotFound()  =
        this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody submission: ProjectSubmission) = this.projectService.submit(submission) // Create Project from submission

    @GetMapping
    fun getAll(): Projects = Projects(projectService.findAll()) // Return a list of all projects

    @GetMapping
    @RequestMapping("/{id}")
    fun get(@PathVariable("id") id: Int): ResponseEntity<Project> = this.projectService.findById(id).orNotFound()

    @PostMapping
    @RequestMapping("/{id}/trains")
    fun submitTrain(@PathVariable("id") projectId: Int, @RequestBody submission: TrainSubmission) : ResponseEntity<RailedTrain>   // Submits a train for a project
        = this.projectService.submitTrain(submission).orNotFound()

    // TODO Probably not needed
    @GetMapping
    @RequestMapping("/{projectId}/dockerHubTrains/{railedTrainId}")
    fun getRailedTrain(
            @PathVariable("projectId") projectId: Int,
            @PathVariable("railedTrainId") railedTrainId: Int) : ResponseEntity<RailedTrain> =
            this.railedTrainService.findById(railedTrainId).orNotFound()
}
