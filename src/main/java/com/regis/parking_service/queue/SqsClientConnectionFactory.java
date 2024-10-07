package com.regis.parking_service.queue;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Component
public class SqsClientConnectionFactory {

    public SqsClient createConnection(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials("52l3irhwekjhil", "asdf8.n23rliw");
        return SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider((AwsCredentialsProvider) new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
