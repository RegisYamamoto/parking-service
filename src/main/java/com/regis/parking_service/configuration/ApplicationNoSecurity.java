//package com.regis.parking_service.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//public class ApplicationNoSecurity {
//
//  // Descomentar este bean caso queira desligar o spring security
//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//    return (web) -> web.ignoring()
//      .requestMatchers(new AntPathRequestMatcher("/**"));
//  }
//}
