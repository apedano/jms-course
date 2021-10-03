package com.apedano.rabbitmq;


import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PublisherIntegrationTest extends BasicTest {

    private final Publisher publisher = new Publisher();

    @Test
    public void publishSingleMessageTest() throws Exception {
        publishMessage("This is the first message sent at " + System.currentTimeMillis(), publisher);
    }

    @Test
    public void publish10MessagesTest() throws Exception {
        for(int i=1; i<=10; i++) {
            publishMessage(createMessage(i), publisher);
        }

    }

}
