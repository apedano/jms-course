package com.apedano.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Slf4j
public class HeadersBasedPublisherTest extends BasicTest{

    private static final String HEADER_1 = "header1";
    private static final String HEADER_2 = "header2";

    @Test
    public void q1PublishedTest() throws Exception {
        Map<String, Object> headers = new HashMap<>();
        //x-match = any for q1 -> only one header is enough
        headers.put(HEADER_1, "Headers-Exchange-Q1");
        HeadersBasedPublisher headersBasedPublisher = new HeadersBasedPublisher(headers);
        publish10Messages(headersBasedPublisher);
    }

    @Test
    public void q2PublishedTest() throws Exception {
        Map<String, Object> headers = new HashMap<>();
        //x-match = all for q2 -> all headers are required
        headers.put(HEADER_1, "Headers-Exchange-Q2");
        headers.put(HEADER_2, "HeaderForAllMatch");
        HeadersBasedPublisher headersBasedPublisher = new HeadersBasedPublisher(headers);
        publish10Messages(headersBasedPublisher);
    }


    @Test
    public void nonPublishedOnQ2Test() throws Exception {
        Map<String, Object> headers = new HashMap<>();
        //x-match = all for q2 -> only one header is not enough
        headers.put(HEADER_1, "Headers-Exchange-Q2");
        HeadersBasedPublisher headersBasedPublisher = new HeadersBasedPublisher(headers);
        publishMessage("Non published message", headersBasedPublisher);
    }


}
