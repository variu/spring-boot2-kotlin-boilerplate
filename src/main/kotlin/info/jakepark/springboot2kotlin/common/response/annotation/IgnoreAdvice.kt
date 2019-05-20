package info.jakepark.springboot2kotlin.common.response.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class IgnoreAdvice(val ignore: Boolean = true)
