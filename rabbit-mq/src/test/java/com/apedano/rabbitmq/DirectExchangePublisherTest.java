package com.apedano.rabbitmq;

import org.junit.Test;

public class DirectExchangePublisherTest extends BasicTest {

    private static final String DIRECT_EXCHANGE_NAME = "Direct-Exchange";
//    private static final String DIRECT_EXCHANGE_Q1 = DIRECT_EXCHANGE_NAME + "-Q1";
//    private static final String DIRECT_EXCHANGE_Q2 = DIRECT_EXCHANGE_NAME + "-Q2";
//    private static final String DIRECT_EXCHANGE_Q3 = DIRECT_EXCHANGE_NAME + "-Q3";
    private static final String ROUTING_KEY_TO_Q1 = "rk1";
    private static final String ROUTING_KEY_TO_Q2 = "rk2";
    private static final String ROUTING_KEY_TO_Q3 = "rk3";


    @Test
    public void publishOnRk1() throws Exception {
        ExchangeAndRoutingKeyBasedPublisher directExchangePublisher =
                new ExchangeAndRoutingKeyBasedPublisher(DIRECT_EXCHANGE_NAME, ROUTING_KEY_TO_Q1);
        directExchangePublisher.publish(createMessage(1));

    }

    @Test
    public void publishOnRk2() throws Exception {
        ExchangeAndRoutingKeyBasedPublisher directExchangePublisher =
                new ExchangeAndRoutingKeyBasedPublisher(DIRECT_EXCHANGE_NAME, ROUTING_KEY_TO_Q2);
        directExchangePublisher.publish(createMessage(1));

    }

    @Test
    public void publishOnRk3() throws Exception {
        ExchangeAndRoutingKeyBasedPublisher directExchangePublisher =
                new ExchangeAndRoutingKeyBasedPublisher(DIRECT_EXCHANGE_NAME, ROUTING_KEY_TO_Q3);
        directExchangePublisher.publish(createMessage(1));

    }


}
