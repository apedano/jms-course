package com.apedano.rabbitmq;


import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PublisherIntegrationTest {

    private void publishMessage(String messageContent) throws Exception {
        Publisher publisher = new Publisher();
        publisher.publish(messageContent);
    }

    @Test
    public void publishSingleMessageTest() throws Exception {
        publishMessage("This is the first message sent at " + System.currentTimeMillis());
    }

    @Test
    public void publish10MessagesTest() throws Exception {
        for(int i=1; i<=10; i++) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
            String messageContent = String.format("Message #%d sent at [%s]", i, formattedDateTime);
            publishMessage(messageContent);
        }

    }

}
