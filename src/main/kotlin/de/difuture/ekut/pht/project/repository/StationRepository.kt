package de.difuture.ekut.pht.project.repository

import de.difuture.ekut.pht.project.domain.Station
import org.springframework.data.jpa.repository.JpaRepository

interface StationRepository: JpaRepository<Station, Int>