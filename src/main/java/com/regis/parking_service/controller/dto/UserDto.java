package com.regis.parking_service.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
  private Long id;
  private UUID uuid;
  private String email;
  private String password;
  private String role;
}
