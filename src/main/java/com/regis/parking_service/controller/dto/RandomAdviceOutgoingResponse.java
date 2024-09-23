package com.regis.parking_service.controller.dto;

import lombok.Data;

@Data
public class RandomAdviceOutgoingResponse {

  private Slip slip;

  @Data
  public class Slip {
    private String id;
    private String advice;
  }
}
