package com.apedano.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class Publisher {

    public void publish(String messageContent) throws Exception {
                String exchange = ""; //we don't use exchange
        String routingKey = "Queue-1";
        AMQP.BasicProperties basicProperties = null;
        try(AutoClosableChannel autoClosableChannel = new AutoClosableChannel()) {
            log.info("Publishing message: {}", messageContent);
            autoClosableChannel.get().basicPublish(exchange, routingKey, basicProperties, messageContent.getBytes());
            log.info("Message published successfully");
        } catch (IOException ioe) {
            log.error("Error while publishing message", ioe);
            throw ioe;
        }

    }

}
