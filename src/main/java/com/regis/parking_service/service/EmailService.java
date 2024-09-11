package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class EmailService {
  public void sendEmails(List<Email> emails) throws ExecutionException, InterruptedException {
    List<CompletableFuture> completableFutures = new ArrayList<>();

    emails.forEach(email -> {
      CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
        log.info("Enviando email para: " + email.getReceiver());
        // Implementar o envio de email
      });
      log.info("Criando o completableFuture para o email: " + email.getReceiver());
      completableFutures.add(completableFuture);
    });

    CompletableFuture<?>[] array = completableFutures.toArray(new CompletableFuture[0]);
    CompletableFuture.allOf(array).get();
    // Quando adicionado o método .get() ele espera todas as
    // threads (completableFutures) terminarem para só depois continuar com o processo

    log.info("Emails enviados com sucesso");
  }
}
