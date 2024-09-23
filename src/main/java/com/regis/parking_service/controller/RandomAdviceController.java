package com.regis.parking_service.controller;

import com.regis.parking_service.controller.dto.RandomAdviceIncomingResponse;
import com.regis.parking_service.service.RandomAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/random-advice")
public class RandomAdviceController {

  @Autowired
  private RandomAdviceService randomAdviceService;

  @GetMapping
  public ResponseEntity<RandomAdviceIncomingResponse> getRandomAdvice() throws IOException, InterruptedException {
    RandomAdviceIncomingResponse response = randomAdviceService.getRandomAdvice();

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
