package com.regis.parking_service.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Email {
  @NotNull
  private String receiver;
  @NotNull
  private String sender;
  @NotNull
  private String subject;
  @NotNull
  private String body;
}
