package com.apedano.rabbitmq;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class BasicTest {
    protected String createMessage(int messageNumber) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        return String.format("Message #%d sent at [%s]", messageNumber, formattedDateTime);
    }

    protected void publish10Messages(Publisher publisher) throws Exception {
        for(int i=1; i<=10; i++) {
            publishMessage(createMessage(i), publisher);
        }
    }

    protected void publishMessage(String messageContent, Publisher publisher) throws Exception {
       publisher.publish(messageContent);
    }




}
