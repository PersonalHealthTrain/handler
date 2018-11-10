package de.difuture.ekut.pht.project.domain
import de.difuture.ekut.pht.lib.data.TrainName
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter
class TrainNameConverter : AttributeConverter<TrainName, String> {

    override fun convertToDatabaseColumn(name: TrainName) =  name.repr
    override fun convertToEntityAttribute(str: String) = TrainName.from(str)
}
