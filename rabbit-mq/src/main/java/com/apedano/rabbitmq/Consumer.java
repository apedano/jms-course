package com.apedano.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Consumer {

    private final DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
        log.info("Consuming message");
        String incomingMessage = new String(delivery.getBody());
        log.info("Consumed message: {}", incomingMessage);
    });

    private final CancelCallback cancelCallback = ((consumerTag) -> {
    });

    public void consumeMessages() throws Exception {
        //we don't close the connection and the channel becuase the consumer runs continuosly in
        //order to consume messages when they are exposed by the queue
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //we set autoAck to true to inform RabbitMQ that the message has been consumed
        channel.basicConsume("Queue-1", true, deliverCallback, cancelCallback);
    }

    /**
     * This is a program always running
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        log.info("Starting consumer application");
        Consumer consumer = new Consumer();
        consumer.consumeMessages();
        log.info("Consumer messages started");
    }


}
