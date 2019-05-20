package info.jakepark.springboot2kotlin.common.response

import info.jakepark.springboot2kotlin.common.handler.MessageHandler
import info.jakepark.springboot2kotlin.common.response.annotation.IgnoreAdvice
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

private val log = LogManager.getLogger()

@RestControllerAdvice(basePackages = arrayOf("info.jakepark.springboot2kotlin"))
class CustomResponseBodyResult : ResponseBodyAdvice<Any> {

    @Autowired
    lateinit var messageHandler: MessageHandler

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(body: Any?, returnType: MethodParameter, selectedContentType: MediaType, selectedConverterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): Any? {

        if (body is BeanPropertyBindingResult) {
            val errors: List<CustomError> = body.getFieldErrors().map { CustomError(it.field, if (it.rejectedValue == null) "" else it.rejectedValue.toString(), it.defaultMessage) }
            return getCustomResponse(ResponseType.BAD_REQUEST, null, errors)
        }

        val ignoreAdvice = returnType.getMethodAnnotation<IgnoreAdvice>(IgnoreAdvice::class.java)
        if (ignoreAdvice != null && ignoreAdvice.ignore) {
            return body
        }

        if (response is ServletServerHttpResponse && response.servletResponse.status == HttpStatus.OK.value()) {
            if (body == null) {
                return getCustomResponse(ResponseType.OK_BUT_NO_CONTENT, body)
            }
        }

        return getCustomResponse(ResponseType.OK, body)
    }

    fun getCustomResponse(responseType: ResponseType, result: Any?): CustomResponse {
        return CustomResponse(responseType.code, messageHandler.getMessage(responseType), result, null, null)
    }

    fun getCustomResponse(responseType: ResponseType, result: Any?, errors: List<CustomError>): CustomResponse {
        return CustomResponse(responseType.code, messageHandler.getMessage(responseType), result, errors, null)
    }

}
