package info.jakepark.springboot2kotlin.business.common

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class PingController {

    @GetMapping("ping")
    fun ping() = "pong"
}
