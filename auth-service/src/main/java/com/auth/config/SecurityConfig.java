package com.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.net.http.HttpRequest;
import java.util.UUID;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

   /* @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers("/test","/login/**").permitAll().anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable).
                 sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                 .formLogin(Customizer.withDefaults());
         return httpSecurity.build();
    }*/

    @Bean
    @Order(1)
    public SecurityFilterChain oAuthServerSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
       OAuth2AuthorizationServerConfigurer oAuth2AuthorizationServerConfigurer =
               new OAuth2AuthorizationServerConfigurer();
       httpSecurity.securityMatcher(oAuth2AuthorizationServerConfigurer.getEndpointsMatcher())
               .csrf(csrf -> csrf.ignoringRequestMatchers(oAuth2AuthorizationServerConfigurer.getEndpointsMatcher()))
               .formLogin(Customizer.withDefaults())
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        oAuth2AuthorizationServerConfigurer.oidc(Customizer.withDefaults());
       httpSecurity.with(oAuth2AuthorizationServerConfigurer, Customizer.withDefaults());
       return httpSecurity.build();
   }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("my-client")
                .clientSecret("{noop}secret")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:8080/login/oauth2/code/my-client")
                .scope("openid")
                .scope("profile")
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }


}
