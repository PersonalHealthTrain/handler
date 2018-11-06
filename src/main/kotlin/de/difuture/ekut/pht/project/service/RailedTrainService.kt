package de.difuture.ekut.pht.project.service

import de.difuture.ekut.pht.project.domain.RailedTrain
import de.difuture.ekut.pht.project.repository.RailedTrainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class RailedTrainService
@Autowired constructor(private val repo: RailedTrainRepository) {

    fun findById(id: Int) : RailedTrain? = repo.findById(id).orElse(null)
}
