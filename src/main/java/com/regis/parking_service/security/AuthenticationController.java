package com.regis.parking_service.security;

import com.regis.parking_service.controller.dto.AuthenticationDto;
import com.regis.parking_service.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(authenticationDto.email(), authenticationDto.password());

    Authentication authenticate =
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    String token = tokenService.generateToken((User) authenticate.getPrincipal());

    return ResponseEntity.status(HttpStatus.OK).body(token);
  }
}
