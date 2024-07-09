package com.regis.parking_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      return httpSecurity
          .authorizeHttpRequests(
              authorizeConfig -> {
                authorizeConfig.requestMatchers("/public/parking-spots").permitAll();
                authorizeConfig.anyRequest().authenticated();
              })
          .build();
    }
}
