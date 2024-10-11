package com.regis.parking_service.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private UUID uuid;
    private String email;
    private String password;
    private String role;
}
