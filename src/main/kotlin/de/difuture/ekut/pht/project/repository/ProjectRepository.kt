package de.difuture.ekut.pht.project.repository

import de.difuture.ekut.pht.project.domain.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Int>
