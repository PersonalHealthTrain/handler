package de.difuture.ekut.pht.project.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "project")
class ProjectProperties {

    var createDummyStations: Boolean? = false
}
