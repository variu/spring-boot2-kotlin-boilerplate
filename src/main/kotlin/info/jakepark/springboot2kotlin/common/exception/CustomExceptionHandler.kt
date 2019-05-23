package info.jakepark.springboot2kotlin.common.exception

import info.jakepark.springboot2kotlin.common.handler.CustomMessageHandler
import info.jakepark.springboot2kotlin.common.response.CustomResponse
import info.jakepark.springboot2kotlin.common.response.ResponseType
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

private val log = LogManager.getLogger()

@ControllerAdvice
class CustomExceptionHandler {

    @Autowired
    private lateinit var customMessageHandler: CustomMessageHandler

    @Autowired
    private lateinit var environment: Environment

    private fun getCustomResponse(responseType: ResponseType, result: Any?, e: Exception): ResponseEntity<CustomResponse> {
        val responseBody = CustomResponse(responseType.code, customMessageHandler.getMessage(responseType), result, null, null)

        // when profile is not production, print debug message.
        if (environment.activeProfiles.any { it.equals("production", true) }) {
            responseBody.debugMessage = e.message
        }

        return ResponseEntity.status(responseType.httpStatus).body(responseBody)
    }

    @ExceptionHandler(Exception::class)
    fun handleInternalServerError(e: Exception): ResponseEntity<CustomResponse> {
        log.error(e.message)
        return getCustomResponse(ResponseType.INTERNAL_SERVER_ERROR, null, e)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ResponseEntity<CustomResponse> {
        return getCustomResponse(ResponseType.BAD_REQUEST_METHOD, null, e)
    }

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(e: ApplicationException): ResponseEntity<CustomResponse> {
        log.debug(e.message)
        return getCustomResponse(e.responseType, e.result, e)
    }

}