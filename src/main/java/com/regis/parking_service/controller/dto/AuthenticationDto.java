package com.regis.parking_service.controller.dto;

import lombok.Data;

@Data
public class AuthenticationDto {
  private String email;
  private String password;
}
