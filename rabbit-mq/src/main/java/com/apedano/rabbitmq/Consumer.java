package com.apedano.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Consumer {

    private final String customConsumerTag;
    private final String queueName;

    public Consumer(String customConsumerTag, String queueName) {
        this.customConsumerTag = customConsumerTag;
        this.queueName = queueName;
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
        channel.basicConsume(queueName, true, customConsumerTag, deliverCallback, cancelCallback);
    }

    /**
     * This is a program always running
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String consumerTag = args.length > 0 ? args[0] : "defaultConsumerTag";
        String queueName = args.length > 1 ? args[1] : "Queue-1";
        log.info("Starting consumer [{}] [queue={}] application", consumerTag, queueName);
        Consumer consumer = new Consumer(consumerTag, queueName);
        consumer.consumeMessages();
        log.info("Consumer messages started");
    }
}
