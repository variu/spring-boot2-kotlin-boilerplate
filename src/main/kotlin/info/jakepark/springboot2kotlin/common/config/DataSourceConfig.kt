package info.jakepark.springboot2kotlin.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["info.jakepark.springboot2kotlin.business.**.repository"])
@EntityScan(basePackages = ["info.jakepark.springboot2kotlin.business.**.entity.**"])
class DataSourceConfig {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    // TODO : QueryDSL
//    @Bean
//    fun queryFactory(): JPAQueryFactory {
//        return JPAQueryFactory(entityManager)
//    }

    @Bean
    fun exceptionTranslation() = PersistenceExceptionTranslationPostProcessor()

}
