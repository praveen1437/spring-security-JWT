package com.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;


@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/oauth2/**").permitAll().anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable).
                 sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         return httpSecurity.build();
    }
}
