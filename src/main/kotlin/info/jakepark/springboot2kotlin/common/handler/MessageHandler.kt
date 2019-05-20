package info.jakepark.springboot2kotlin.common.handler

import info.jakepark.springboot2kotlin.common.response.ResponseType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class MessageHandler {

    @Autowired
    lateinit var messageSource: MessageSource

    fun getMessage(responseType: ResponseType): String {
        return messageSource.getMessage(responseType.message, null, LocaleContextHolder.getLocale())
    }

    fun getMessage(responseType: ResponseType, vararg args: Any): String {
        return messageSource.getMessage(responseType.message, args, LocaleContextHolder.getLocale())
    }

    fun getMessage(responseType: ResponseType, locale: Locale): String {
        return messageSource.getMessage(responseType.message, null, locale)
    }

    fun getMessage(responseType: ResponseType, vararg args: Any, locale: Locale): String {
        return messageSource.getMessage(responseType.message, args, locale)
    }


}