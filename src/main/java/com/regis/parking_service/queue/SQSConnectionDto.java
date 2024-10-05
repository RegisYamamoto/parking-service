package com.regis.parking_service.queue;

import com.amazon.sqs.javamessaging.SQSConnection;
import lombok.Builder;
import lombok.Data;

import javax.jms.Queue;
import javax.jms.Session;

@Data
@Builder
public class SQSConnectionDto {
    private SQSConnection connection;
    private Session session;
    private Queue queue;
}
