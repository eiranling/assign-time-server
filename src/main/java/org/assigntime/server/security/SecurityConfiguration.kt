package org.assigntime.server.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun tokenStore() : TokenStore {
        return TokenStore()
    }

    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/users", "/api/login")
                    .permitAll().and().csrf().disable()
        }




    }
}