package com.regis.parking_service.service;

import com.regis.parking_service.controller.dto.Email;
import com.regis.parking_service.queue.SqsMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class EmailService {

  @Autowired
  private SqsMessageSender sqsMessageSender;

  public void sendEmails(List<Email> emails) throws ExecutionException, InterruptedException {
    List<CompletableFuture> completableFutures = new ArrayList<>();

    emails.forEach(email -> {
      CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
          try {
            log.info("Enviando email para: " + email.getReceiver());
            // Enviar email para algum serviço de emails com o Madrill por exemplo
          } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao enviar a mensagem para a fila do SQS", e);
          }
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
