package info.jakepark.springboot2kotlin.common.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        super.addResourceHandlers(registry)
    }

    override fun getValidator(): Validator? {
        val bean = LocalValidatorFactoryBean()
                .apply {
                    setValidationMessageSource(messageSource())
                }
        return bean
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(0, MappingJackson2HttpMessageConverter(objectMapper()))
    }

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
                .apply {
                    setBasename("classpath:messages/messages")
                    setDefaultEncoding(Charsets.UTF_8.displayName())
                }
        return messageSource
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        val builder = Jackson2ObjectMapperBuilder()
                .apply {
                    featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    serializationInclusion(JsonInclude.Include.NON_NULL)
                }

        return builder.build()
    }

//    @Bean
//    fun corsFilter(): FilterRegistrationBean<CorsFilter> {
//        val config = CorsConfiguration()
//                .apply {
//                    allowCredentials = true
//                    allowedOrigins = listOf("*")
//                    allowedHeaders = listOf("*")
//                    allowedMethods = listOf("*")
//                }
//
//        val source = UrlBasedCorsConfigurationSource()
//                .apply {
//                    registerCorsConfiguration("/**", config)
//                }
//
//        val bean = FilterRegistrationBean(CorsFilter(source))
//                .apply {
//                    order = Ordered.HIGHEST_PRECEDENCE
//                }
//
//        return bean
//    }


}
