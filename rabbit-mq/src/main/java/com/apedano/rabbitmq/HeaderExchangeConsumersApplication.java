package com.apedano.rabbitmq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeaderExchangeConsumersApplication {
    public static final void main(String[] args) {
        Thread consumerQ1 = new Thread() {
            @Override
            public void run() {
                try {
                    Consumer consumer = new Consumer("HeaderExchangeConsumerQ1", "Headers-Exchange-Q1");
                    consumer.consumeMessages();
                } catch (Exception e) {
                    log.error("Error consuming messages on Q1", e);
                }
            }
        };

        Thread consumerQ2 = new Thread() {
            @Override
            public void run() {
                try {
                    Consumer consumer = new Consumer("HeaderExchangeConsumerQ2", "Headers-Exchange-Q2");
                    consumer.consumeMessages();
                } catch (Exception e) {
                    log.error("Error consuming messages on Q2", e);
                }
            }
        };

        consumerQ1.start();
        consumerQ2.start();
    }
}
