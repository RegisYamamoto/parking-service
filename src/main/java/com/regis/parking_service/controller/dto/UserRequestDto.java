package com.regis.parking_service.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserRequestDto {
  private Long id;
  private UUID uuid;
  private String email;
  private String password;
  private String role;
}
