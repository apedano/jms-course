package com.apedano.rabbitmq;

import org.junit.Test;

public class FanoutExchangePublisherTest extends BasicTest {

    private static final String FANOUT_EXCHANGE_NAME = "Fanout-Exchange";
    private static final String NON_RELEVANT_ROUTING_KEY = "";

    @Test
    public void publishOnFanoutExchange() throws Exception {
        Publisher publisher =
                new ExchangeAndRoutingKeyBasedPublisher(FANOUT_EXCHANGE_NAME, NON_RELEVANT_ROUTING_KEY);
        this.publish10Messages(publisher);
    }



}
