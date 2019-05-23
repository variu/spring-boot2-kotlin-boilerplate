package info.jakepark.springboot2kotlin.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("spring.redis")
class RedisProperties {
    lateinit var host: String
}
