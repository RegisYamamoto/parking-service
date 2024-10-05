package com.regis.parking_service.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Slf4j
@Component
public class SqsMessageSender {

    @Autowired
    private SqsConnectionFactory sqsConnectionFactory;

    public void send() throws JMSException {
        SQSConnectionDto sqsConnectionDto = sqsConnectionFactory.createConnection();

        // Create a producer for the 'queue-name'
        MessageProducer producer = sqsConnectionDto.getSession().createProducer(sqsConnectionDto.getQueue());

        // Create the text message
        TextMessage message =
                sqsConnectionDto.getSession().createTextMessage("{\"sender\":\"contato@torresinglaterra.com.br\",\"receiver\":" +
                        "\"carlos@gmail.com\",\"subject\":\"Confirmação de cadastro\",\"body\":" +
                        "\"Sua vaga foi cadastrada com sucesso.\"}");

        // Send the message
        producer.send(message);
        log.info("Mensagem enviada com sucesso com o id da mensagem: " + message.getJMSMessageID());
    }
}
