package de.difuture.ekut.pht.project.controller

import de.difuture.ekut.pht.project.service.TrainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trains")
@CrossOrigin
class TrainController
@Autowired constructor(private val trainService: TrainService) {

    @GetMapping
    fun getAll() = trainService.getTrains()
}
