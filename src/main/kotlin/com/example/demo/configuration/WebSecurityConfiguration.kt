package com.example.demo.configuration

import com.example.demo.service.CustomUserServiceDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class WebSecurityConfiguration {

    @Bean
    @Throws(java.lang.Exception::class)
    fun authManager(
        http: HttpSecurity,
        bCryptPasswordEncoder: BCryptPasswordEncoder,
        userDetailsService: CustomUserServiceDetails
    ): AuthenticationManager? {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder)
            .and()
            .build()
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.authorizeHttpRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/access-denied").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/error").permitAll() //.antMatchers("/admin/**").permitAll()
            .antMatchers("/upload/**").hasAnyAuthority("ROLE_VIP")
            .antMatchers("/addPerson/**").hasAnyAuthority("ROLE_VIP")
            .antMatchers("/client/**").hasAnyAuthority("ROLE_VIP")
            .antMatchers("/clients").hasAnyAuthority("ROLE_USER","ROLE_VIP")
            .and() //.csrf().disable()
            .formLogin()
//            .loginPage("/login").failureUrl("/login?error=true")
            .defaultSuccessUrl("/")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .logout()
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun accessDeniedHandler(): AccessDeniedHandler {
        return CustomAccessDeniedHandler()
    }

}
