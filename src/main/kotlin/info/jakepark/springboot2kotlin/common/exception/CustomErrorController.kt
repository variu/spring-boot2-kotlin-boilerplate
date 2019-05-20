package info.jakepark.springboot2kotlin.common.exception

import info.jakepark.springboot2kotlin.common.handler.CustomMessageHandler
import info.jakepark.springboot2kotlin.common.response.CustomResponse
import info.jakepark.springboot2kotlin.common.response.ResponseType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import java.util.*

const val PATH = "error"

@RestController
class CustomErrorController : ErrorController {


    @Autowired
    lateinit var errorAttributes: ErrorAttributes

    @Autowired
    lateinit var customMessageHandler: CustomMessageHandler

    override fun getErrorPath(): String {
        return PATH
    }

    @RequestMapping(PATH)
    fun error(request: WebRequest): ResponseEntity<CustomResponse> {
        val errors = this.getErrorAttributes(request)
        val path = errors.get("path")
        val error = errors.get("error")
        val message = errors.get("message") as String
        val status = errors.get("status") as Int
        val responseType = ResponseType.NOT_FOUND
        return ResponseEntity
                .status(HttpStatus.valueOf(status))
                .body(CustomResponse(responseType.code, customMessageHandler.getMessage(responseType), path, null, null))
    }

    private fun getErrorAttributes(request: WebRequest): Map<String, Any> {
        val map = HashMap<String, Any>()
        map.putAll(this.errorAttributes.getErrorAttributes(request, true))
        return map
    }

}