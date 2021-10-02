package com.apedano.rabbitmq;


import org.junit.Test;

public class PublisherIntegrationTest {

    @Test
    public void publishMessageTest() throws Exception {
        String messageContent = "This is the first message sent at " + System.currentTimeMillis();
        Publisher publisher = new Publisher();
        publisher.publish(messageContent);

    }

}
