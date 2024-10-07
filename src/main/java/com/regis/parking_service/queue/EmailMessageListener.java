package com.regis.parking_service.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.time.Duration;

@Slf4j
@Component
public class EmailMessageListener {

    private static final int MAX_PROCESSING_ATTEMPT = 16;
    private static final int RETRY_DELAY_MULTIPLIER = 15;

    @Autowired
    private SqsConnectionFactory sqsConnectionFactory;

    @Autowired
    private SqsMessageSender sqsMessageSender;

    public void onMessage() throws JMSException {
        SQSConnectionDto sqsConnectionDto = sqsConnectionFactory.createConnection();

        // Create a consumer for the 'queue-name'
        MessageConsumer consumer = sqsConnectionDto.getSession().createConsumer(sqsConnectionDto.getQueue());
        // Start receiving incoming messages
        sqsConnectionDto.getConnection().start();

        // Receive a message from 'queue-name' and wait up to 1 second
        Message receivedMessage = consumer.receive(1000);

        // Cast the received message as TextMessage and display the text
        EmailMessage emailMessage = new EmailMessage(receivedMessage);
        try {
            log.info("Tentativa de processamento {}/{} da messageId {} iniciada com a mensagem: {}",
                    emailMessage.getProcessingAttempt(),
                    MAX_PROCESSING_ATTEMPT,
                    emailMessage.getMessageId(),
                    emailMessage.getText());

            String message = (emailMessage).getText();

            // Enviar mensagem para o serviço de mensagem como o Madrill
        } catch (Exception ex) {
            retry(emailMessage, ex);
        }

        receivedMessage.acknowledge();
    }

    private void retry(EmailMessage emailMessage, Throwable ex) {
        int processingAttempt = emailMessage.getProcessingAttempt();

        if (processingAttempt >= MAX_PROCESSING_ATTEMPT) {
            log.error(
                    String.format(
                            "Tentativa de processamento %d/%d da messageId %s failed! Ignorando a mensagem: %s",
                            processingAttempt,
                            MAX_PROCESSING_ATTEMPT,
                            emailMessage.getMessageId(),
                            emailMessage.getText()),
                    ex);
            return;
        }

        Duration retryDelay = emailMessage.calculateRetryDelay(RETRY_DELAY_MULTIPLIER);
        log.warn(
                String.format(
                        "Tentativa de processamento %d/%d da messageId %s failed! Reenviando a mensagem com um delay de %s.",
                        processingAttempt,
                        MAX_PROCESSING_ATTEMPT,
                        emailMessage.getMessageId(),
                        retryDelay),
                ex);

        sqsMessageSender.resend(emailMessage.getText(), emailMessage.createRetryMessageAttributes(), retryDelay);
    }
}
