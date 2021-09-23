package com.apedano.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

class AutoClosableChannel implements AutoCloseable {
    protected ConnectionFactory connectionFactory = new ConnectionFactory();
    private final Connection connection;
    private final Channel channel;

    public AutoClosableChannel() throws IOException, TimeoutException {
        this.connection = connectionFactory.newConnection();
        this.channel = connection.createChannel();
    }

    public Channel get() {
        return this.channel;
    }

    @Override
    public void close() throws Exception {
        this.channel.close();
        this.connection.close();
    }
}
