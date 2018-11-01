package de.difuture.ekut.pht.project.service

import de.difuture.ekut.pht.project.api.ProjectSubmission
import de.difuture.ekut.pht.project.domain.Project
import de.difuture.ekut.pht.project.repository.ProjectRepository
import de.difuture.ekut.pht.project.repository.StationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService
@Autowired constructor(
        private val projectRepo: ProjectRepository,
        private val stationRepo: StationRepository) {

    /**
     * Creates a new Project from the provided [ProjectSubmission]
     *
     * @param submission The [ProjectSubmission] from which a new project should be generated
     * @return [Project] The newly generated project
     */
    fun submit(submission: ProjectSubmission) : Project =
            stationRepo.findAllById(submission.stations).toSet().let { concernedStations ->

                this.projectRepo.save(Project(0, submission.title, submission.description, concernedStations))
            }
}
