package de.difuture.ekut.pht.project.domain
import de.difuture.ekut.pht.lib.data.TrainName
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter
class TrainNameSetConverter : AttributeConverter<Set<TrainName>, String> {

    override fun convertToDatabaseColumn(set: Set<TrainName>) = set.joinToString("$") { it.repr }
    override fun convertToEntityAttribute(joined: String) = joined.split("$").map { TrainName.from(it) }.toSet()
}
