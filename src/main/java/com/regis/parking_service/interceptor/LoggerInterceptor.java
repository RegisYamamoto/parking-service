package com.regis.parking_service.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.UUID;

@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String transactionId = request.getHeader("transaction-id");
    if (transactionId == null) {
      transactionId = UUID.randomUUID().toString();
    }
    MDC.put("transactionId", transactionId);

    log.info("postHandle");
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
      throws Exception {
    log.info("postHandle");
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    MDC.clear();
    log.info("afterCompletion");
  }
}
