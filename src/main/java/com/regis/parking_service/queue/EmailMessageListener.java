package com.regis.parking_service.queue;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Slf4j
@Component
public class EmailMessageListener {

    @Autowired
    private SqsConnectionFactory sqsConnectionFactory;

    public void onMessage() throws JMSException {
        SQSConnectionDto sqsConnectionDto = sqsConnectionFactory.createConnection();

        // Create a consumer for the 'queue-name'
        MessageConsumer consumer = sqsConnectionDto.getSession().createConsumer(sqsConnectionDto.getQueue());
        // Start receiving incoming messages
        sqsConnectionDto.getConnection().start();

        // Receive a message from 'queue-name' and wait up to 1 second
        Message receivedMessage = consumer.receive(1000);

        // Cast the received message as TextMessage and display the text
        if (receivedMessage != null) {
            log.info("Mensagem recebida : " + ((TextMessage) receivedMessage).getText());
            String message = ((TextMessage) receivedMessage).getText();

            // Enviar mensagem para o serviço de mensagem como o Madrill
        }
    }
}
