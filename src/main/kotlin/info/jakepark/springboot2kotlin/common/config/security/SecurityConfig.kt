package info.jakepark.springboot2kotlin.common.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var dataSource: DataSource

    override fun configure(web: WebSecurity) {
        super.configure(web)
    }

    override fun configure(http: HttpSecurity) {
        http
                .anonymous()
                .and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/ping", "/api/test/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService(userDetailService())
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun userDetailService(): UserDetailsService {
        val userDetailsService = JdbcDaoImpl().apply {
            setDataSource(dataSource!!)
        }
        return userDetailsService
    }
}

