package com.apedano.rabbitmq;

import org.junit.Test;

public class ConsumerIntegrationTest {

    @Test
    public void consumeMessageTest() throws Exception {
        Consumer consumer = new Consumer();
        consumer.consumeMessages();
    }
}
