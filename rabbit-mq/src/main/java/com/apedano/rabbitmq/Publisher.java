package com.apedano.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class Publisher {

    private static final String HEADERS_EXCHANGE_NAME = "";
    private static final String NON_RELEVANT_ROUTING_KEY = "";

    public void publish(String messageContent) throws Exception {

        try(AutoClosableChannel autoClosableChannel = new AutoClosableChannel()) {
            log.info("Publishing message [exchange:{}, routingKey:{}]: {}", HEADERS_EXCHANGE_NAME, NON_RELEVANT_ROUTING_KEY, messageContent);
            autoClosableChannel.get().basicPublish(getExchange(), getRoutingKey(),
                    new AMQP.BasicProperties().builder().headers(getHeaderMap()).build(), messageContent.getBytes());
            log.info("Message published successfully");
        } catch (IOException ioe) {
            log.error("Error while publishing message", ioe);
            throw ioe;
        }

    }

    private AMQP.BasicProperties createProperties() {
        if(getHeaderMap() == null) {
            return null;
        }
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
        return basicProperties.builder().headers(getHeaderMap()).build();

    }

    protected String getExchange() {
        return "";
    }

    protected String getRoutingKey() {
        return "Queue-1";
    }

    protected Map<String, Object> getHeaderMap() {
        return null;
    }


}
