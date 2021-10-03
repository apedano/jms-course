package com.apedano.rabbitmq;

import java.util.Map;

public class HeadersBasedPublisher extends Publisher {

    private static final String HEADERS_EXCHANGE_NAME = "Headers-Exchange";
    private final Map<String, Object> headers;

    public HeadersBasedPublisher(Map<String, Object> headers) {
        this.headers = headers;
    }

    @Override
    protected String getExchange() {
        return HEADERS_EXCHANGE_NAME;
    }

    @Override
    protected String getRoutingKey() {
        return "";
    }

    @Override
    protected Map<String, Object> getHeaderMap() {
        return headers;
    }
}
