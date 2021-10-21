package com.apedano.rabbitmqspring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class RabbitMQConsumer {

    private static final String QUEUE_NAME = "Direct-Exchange-Q1";
    private static final String QUEUE_NAME_HEADERS = "Headers-Exchange-Q2";

    @RabbitListener(queues= QUEUE_NAME)
    public void getMessage(Person p) {
        log.info("Received message from {}: {}", QUEUE_NAME, p);
    }

    @RabbitListener(queues= QUEUE_NAME_HEADERS)
    public void getMessageFromHeaderExchangeQueue(byte[] messageBody) throws IOException, ClassNotFoundException {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(messageBody);
        ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream)) {
            Person p = (Person) objectInput.readObject();
            log.info("Received message from {}: {}", QUEUE_NAME_HEADERS, p);
        }

    }

}
