package com.regis.parking_service.queue;

import lombok.Data;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Data
public class EmailMessage {

    private static final String INITIAL_MESSAGE_ID_PROPERTY_NAME = "initialMessageId";
    private static final String PROCESSING_ATTEMPT_PROPERTY_NAME = "processingAttempt";
    private static final int INITIAL_PROCESSING_ATTEMPT = 1;

    private Message message;
    private String messageId;
    private String initialMessageId;
    private int processingAttempt;
    private String text;

    public EmailMessage(javax.jms.Message message) throws JMSException {
        this.message = message;
        this.messageId = message.getJMSMessageID().replace("ID", "");
        this.initialMessageId = (message.propertyExists(INITIAL_MESSAGE_ID_PROPERTY_NAME)
                ? message.getStringProperty(INITIAL_MESSAGE_ID_PROPERTY_NAME)
                : messageId);
        this.processingAttempt = (message.propertyExists(PROCESSING_ATTEMPT_PROPERTY_NAME)
                ? message.getIntProperty(PROCESSING_ATTEMPT_PROPERTY_NAME)
                : INITIAL_PROCESSING_ATTEMPT);
        this.text = ((TextMessage) message).getText();
    }

    public Duration calculateRetryDelay(int retryDelayMultiplier) {
        return Duration.ofSeconds((long) this.processingAttempt * retryDelayMultiplier);
    }

    public Map<String, MessageAttributeValue> createRetryMessageAttributes() {
        Map<String, MessageAttributeValue> attributes = new HashMap<>();
        attributes.put(INITIAL_MESSAGE_ID_PROPERTY_NAME, createStringAttributeValue(this.initialMessageId));
        attributes.put(PROCESSING_ATTEMPT_PROPERTY_NAME, createNumericAttributeValue(this.processingAttempt + 1));
        return attributes;
    }

    private MessageAttributeValue createStringAttributeValue(String value) {
        return MessageAttributeValue.builder()
                .stringValue(String.valueOf(value))
                .dataType("String")
                .build();
    }

    private MessageAttributeValue createNumericAttributeValue(int value) {
        return MessageAttributeValue.builder()
                .stringValue(String.valueOf(value))
                .dataType("Number")
                .build();
    }
}
