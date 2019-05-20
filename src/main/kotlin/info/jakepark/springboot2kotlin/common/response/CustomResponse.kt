package info.jakepark.springboot2kotlin.common.response

data class CustomResponse(var code: String, var message: String, var result: Any?, var erros: List<CustomError>?, var debugMessage: String?)