package com.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //All request should be authenticated
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        //If request is not authenticated,web page is shown
        http.httpBasic(Customizer.withDefaults());
        //CSRF->GET,POST
        http.csrf().disable();
        return http.build();
    }
}
