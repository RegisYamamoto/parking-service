package com.regis.parking_service.health;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

  private HikariDataSource dataSource;
  private static final int TIMEOUT = 5;

  @Override
  public Health health() {
    try {
      String connection = "Connectado";

      if (connection.equals("Connectado")) {
        return Health.up().build();
      } else {
        return Health.down().build();
      }
    } catch (RuntimeException e) {
      return Health.down(e).build();
    }
  }
}
