package com.regis.parking_service.configuration;

import com.regis.parking_service.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

  // Configuração para poder funcionar o HandlerInterceptor
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggerInterceptor());
  }

  // Configuração para poder funcionar a paginação (Pageable)
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
  }
}
