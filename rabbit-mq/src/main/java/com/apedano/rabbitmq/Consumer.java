package com.apedano.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Consumer {

    private final String customConsumerTag;

    public Consumer(String customConsumerTag) {
        this.customConsumerTag = customConsumerTag;
    }

    private final DeliverCallback deliverCallback = ((consumerTag, delivery) -> {
        log.info("[{}] Consuming message", consumerTag);
        String incomingMessage = new String(delivery.getBody());
        log.info("[{}] Consumed message: {}", consumerTag, incomingMessage);
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
        channel.basicConsume("Queue-1", true, customConsumerTag, deliverCallback, cancelCallback);
    }

    /**
     * This is a program always running
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String consumerTag = args.length > 0 ? args[0] : "defaultConsumerTag";
        log.info("Starting consumer [{}] application", consumerTag);
        Consumer consumer = new Consumer(consumerTag);
        consumer.consumeMessages();
        log.info("Consumer messages started");
    }
}
