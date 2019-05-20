package info.jakepark.springboot2kotlin.common.exception

import info.jakepark.springboot2kotlin.common.response.ResponseType

class ApplicationException(val responseType: ResponseType) : RuntimeException(responseType.message) {
    val args: Array<Any>? = null
    val result: Any? = null
}