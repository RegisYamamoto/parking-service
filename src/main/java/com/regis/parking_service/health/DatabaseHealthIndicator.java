package com.regis.parking_service.health;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

  private HikariDataSource dataSource;
  private static final int TIMEOUT = 5;

  @Override
  public Health health() {
    try {
      Connection connection = dataSource.getConnection();
      boolean valid = connection.isValid(TIMEOUT);
      if (valid) {
        return Health.up().build();
      } else {
        return Health.down().build();
      }
    } catch (SQLException e) {
      return Health.down(e).build();
    }
  }
}
