package de.difuture.ekut.pht.project.domain

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class RouteConverter : AttributeConverter<List<Int>, String> {

    override fun convertToDatabaseColumn(route: List<Int>) =  route.joinToString(separator = ",")
    override fun convertToEntityAttribute(str: String) = str.split(",").map { it.toInt() }
}
