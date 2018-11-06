package de.difuture.ekut.pht.project.repository

import de.difuture.ekut.pht.project.domain.RailedTrain
import org.springframework.data.jpa.repository.JpaRepository

interface RailedTrainRepository: JpaRepository<RailedTrain, Int>
