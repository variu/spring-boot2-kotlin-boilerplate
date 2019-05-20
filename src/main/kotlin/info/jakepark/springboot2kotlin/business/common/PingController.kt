package info.jakepark.springboot2kotlin.business.common

import org.apache.logging.log4j.LogManager
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = LogManager.getLogger()

@RestController
@RequestMapping("api", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class PingController {

    @GetMapping("ping")
    fun ping() = "pong"

    @GetMapping("test")
    fun test() {
        log.info("This is log test!!")
    }
}
