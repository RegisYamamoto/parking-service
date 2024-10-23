package com.regis.parking_service.controller;

import com.regis.parking_service.controller.dto.Email;
import com.regis.parking_service.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/emails")
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class EmailController {

  @Autowired
  private EmailService emailService;

  @PostMapping
  public ResponseEntity<String> sendEmails(@RequestBody @Valid List<Email> emails) {
    try {
      emailService.sendEmails(emails);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (ExecutionException | InterruptedException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar email. Mensagem de erro: " + e.getMessage());
    }
  }
}
