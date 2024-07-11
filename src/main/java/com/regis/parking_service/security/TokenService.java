package com.regis.parking_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.regis.parking_service.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TokenService {

  @Value("&{api.security.token.secret}")
  private String secret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
          .withIssuer("auth-api")
          .withSubject(user.getUsername())
          .withExpiresAt(generateExpirationDate())
          .sign(algorithm);
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Erro ao tentar gerar o token", e);
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
          .withIssuer("auth-api")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException e) {
      return "";
    }
  }

  private Instant generateExpirationDate() {
    LocalDateTime localDateTime = LocalDateTime.now().plusHours(2);
    ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
    Instant instant = zonedDateTime.toInstant();
    return instant;
  }
}
