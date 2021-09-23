package com.apedano.rabbitmq;


import org.junit.Test;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.TimeoutException;

public class PublisherIntegrationTest {

    @Test
    public void publishMessageTest() throws Exception {
        String messageContent = "This is the first message sent at " + System.currentTimeMillis();
        Publisher publisher = new Publisher();
        publisher.publish(messageContent);

    }

}
