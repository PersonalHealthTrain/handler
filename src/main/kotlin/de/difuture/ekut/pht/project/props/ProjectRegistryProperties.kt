package de.difuture.ekut.pht.project.props

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.net.URI

@Configuration
@ConfigurationProperties(prefix = "project.registry")
class ProjectRegistryProperties {

    // The LocalTrain Registry that should be checked for new dockerHubTrains
    lateinit var uri: URI

    // Username and password this service uses for staging
    lateinit var username: String
    lateinit var password: String
}
