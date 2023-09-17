package com.vitorfigueired0.taskforge.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vitorfigueired0.taskforge.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${jwt.secret}")
  private String secret;

  public String createToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      String token = JWT.create()
        .withIssuer("task-forge")
        .withSubject(user.getEmail())
        .withExpiresAt(getExpiration())
        .sign(algorithm);

      return token;
    } catch (JWTCreationException exception) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while token generating");
    }
  }

  public String validateToken(String token) {
    try{
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
        .withIssuer("task-forge")
        .build()
        .verify(token)
        .getSubject();

    } catch (JWTVerificationException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while token verification");
    }
  }

  private static Instant getExpiration() {
    return LocalDateTime.now().plusDays(25).toInstant(ZoneOffset.of("-03:00"));
  }
}
