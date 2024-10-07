package com.regis.parking_service.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import java.time.Duration;
import java.util.Map;

@Slf4j
@Component
public class SqsMessageSender {

    @Autowired
    private SqsConnectionFactory sqsConnectionFactory;

    @Autowired
    private SqsClientConnectionFactory sqsClientConnectionFactory;

    public void send() throws JMSException {
        SQSConnectionDto sqsConnectionDto = sqsConnectionFactory.createConnection();

        // Create a producer for the 'queue-name'
        MessageProducer producer = sqsConnectionDto.getSession().createProducer(sqsConnectionDto.getQueue());

        // Create the text message
        TextMessage message =
                sqsConnectionDto.getSession().createTextMessage("{message}");

        // Send the message
        producer.send(message);
        log.info("Mensagem enviada com sucesso com o id da mensagem: " + message.getJMSMessageID());
    }

    public void resend(String text, Map<String, MessageAttributeValue> attributes, Duration delay) {
        SqsClient sqsClient = sqsClientConnectionFactory.createConnection();

        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(
                builder -> builder
                        .queueUrl("https://sqs.us-east-1.amazonaws.com/123412341234/queue-name")
                        .messageBody(text)
                        .messageAttributes(attributes)
                        .delaySeconds((int) delay.getSeconds()));

        log.info("Mensagem reenviada: {}", sendMessageResponse.messageId());
    }
}
