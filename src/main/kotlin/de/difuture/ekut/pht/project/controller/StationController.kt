package de.difuture.ekut.pht.project.controller

import de.difuture.ekut.pht.project.api.Stations
import de.difuture.ekut.pht.project.service.StationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/station")
@CrossOrigin
class StationController
@Autowired constructor(private val service: StationService) {

    @GetMapping
    fun get() = Stations(service.findAll())
}
