package com.regis.parking_service.queue;

import static com.amazonaws.services.sqs.AmazonSQSClientBuilder.EndpointConfiguration;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

@Slf4j
@Component
public class SqsConnectionFactory {

    public SQSConnectionDto createConnection() throws JMSException {

        // Create client
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("52l3irhwekjhil", "asdf8.n23rliw");
        EndpointConfiguration endpointConfiguration =
                new EndpointConfiguration("https://sqs.us-east-1.amazonaws.com/123412341234", "us-east-1");
        final AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withEndpointConfiguration(endpointConfiguration)
                .build();

        // Create a new connection factory with all defaults (credentials and region) set automatically
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                amazonSQS
        );

        // Create the connection.
        SQSConnection connection = connectionFactory.createConnection();

        // Create the nontransacted session with AUTO_ACKNOWLEDGE mode
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a queue identity and specify the queue name to the session
        Queue queue = session.createQueue("queue-name");

        SQSConnectionDto sqsConnectionDto = SQSConnectionDto.builder()
                .session(session)
                .queue(queue)
                .build();

        return sqsConnectionDto;
    }
}
