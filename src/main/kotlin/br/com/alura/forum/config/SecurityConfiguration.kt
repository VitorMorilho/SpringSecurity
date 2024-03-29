package br.com.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val user: UserDetailsService
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.
        authorizeRequests()?.
        antMatchers("/topicos")?.hasAuthority("LEITURA_ESCRITA")?.
        anyRequest()?.
        authenticated()?.
        and()?.
        sessionManagement()?.
        sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.
        and()?.
        formLogin()?.
        disable()?.
        httpBasic()
    }

    @Bean
    fun bCryptPasswordEnconder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(user)?.passwordEncoder(bCryptPasswordEnconder())
    }


}