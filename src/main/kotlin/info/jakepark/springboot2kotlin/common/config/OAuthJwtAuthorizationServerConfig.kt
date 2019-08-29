package info.jakepark.springboot2kotlin.common.config

import info.jakepark.springboot2kotlin.common.properties.JwtProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.security.KeyPair
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class OAuthJwtAuthorizationServerConfig : AuthorizationServerConfigurer {

    @Autowired
    lateinit var dataSource: DataSource

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtProperties: JwtProperties

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.apply {
            passwordEncoder(passwordEncoder)
            tokenKeyAccess("permitAll()")
            checkTokenAccess("isAuthenticated()")
        }
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.jdbc(dataSource)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!.apply {
            authenticationManager(authenticationManager)
            accessTokenConverter(jwtAccessTokenConverter())
            tokenStore(tokenStore())
        }
    }

    @Bean
    fun tokenStore() = JwtTokenStore(jwtAccessTokenConverter())

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        return JwtAccessTokenConverter()
                .apply {
//                    setSigningKey("test")
                    setKeyPair(keyPair(keyStoreKeyFactory()))
                }
    }

    private fun keyStoreKeyFactory() : KeyStoreKeyFactory {
        return KeyStoreKeyFactory(jwtProperties.keyStore, jwtProperties.keyStorePassword.toCharArray())
    }

    private fun keyPair(keyStoreKeyFactory: KeyStoreKeyFactory): KeyPair {
        return keyStoreKeyFactory.getKeyPair(jwtProperties.keyPairAlias, jwtProperties.keyPairPassword.toCharArray())
    }
}



/*
// TODO : resource server에 추가

    public TokenStore tokenStore() {
        return new JwtTokenStore(resourceAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter resourceAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(yenaAppProperties.getX509PublicKey());
        return converter;
    }

    @Bean
    public DefaultTokenServices resourceTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }
    */

