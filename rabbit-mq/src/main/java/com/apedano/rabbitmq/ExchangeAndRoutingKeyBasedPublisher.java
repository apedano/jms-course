package com.apedano.rabbitmq;

public class ExchangeAndRoutingKeyBasedPublisher extends Publisher {

    private final String directExchangeName;
    private final String routingKey;

    public ExchangeAndRoutingKeyBasedPublisher(String directExchangeName, String routingKey) {
        this.directExchangeName = directExchangeName;
        this.routingKey = routingKey;
    }

    @Override
    protected String getExchange() {
        return directExchangeName;
    }

    @Override
    protected String getRoutingKey() {
        return routingKey;
    }
}
