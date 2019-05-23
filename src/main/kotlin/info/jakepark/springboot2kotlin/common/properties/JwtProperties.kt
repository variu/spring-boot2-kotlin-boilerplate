package info.jakepark.springboot2kotlin.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
@ConfigurationProperties("security.jwt")
class JwtProperties {
    lateinit var keyStore: Resource
    lateinit var keyStorePassword: String
    lateinit var keyPairAlias: String
    lateinit var keyPairPassword: String
}
