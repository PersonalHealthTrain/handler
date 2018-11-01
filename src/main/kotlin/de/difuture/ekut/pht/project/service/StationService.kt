package de.difuture.ekut.pht.project.service

import de.difuture.ekut.pht.project.domain.Station
import de.difuture.ekut.pht.project.props.ProjectProperties
import de.difuture.ekut.pht.project.repository.StationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class StationService
@Autowired constructor(
        private val repo: StationRepository,
        props: ProjectProperties) {

    init {
        if (props.createDummyStations!!) {
            listOf("University of Tuebingen",
                    "University of Ulm",
                    "University of Munich").forEach {
                this.createStation(it)
            }
        }
    }

    fun findAll(): List<Station> = repo.findAll()

    fun createStation(name: String) : Station = this.repo.save(Station(0, name))
}
