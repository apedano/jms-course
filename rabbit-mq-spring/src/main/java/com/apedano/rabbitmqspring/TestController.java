package com.apedano.rabbitmqspring;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;



    @GetMapping(value="publishDirectAndFanout/{name}")
    public TestResponse testApi(@PathVariable("name") String name) {
        Person person = new Person(1, name);
        String routingKey = "rk1";
        //It will create a Base64 encoded string with
        // content_type:	application/x-java-serialized-object
        rabbitTemplate.convertAndSend("Direct-Exchange", routingKey, person);
        rabbitTemplate.convertAndSend("Fanout-Exchange", "", person);
        return new TestResponse(person, "Success");
    }

    @GetMapping(value="publishHeader/{name}")
    public TestResponse testHeaderExchange(@PathVariable("name") String name) throws IOException {
        Person person = new Person(1, name);
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutput objectOutput = new ObjectOutputStream(baos)) {
            String headerExchangeName = "Headers-Exchange";
            objectOutput.writeObject(person);
            Message message = MessageBuilder.withBody(baos.toByteArray())
                    .setHeader("header1", "Headers-Exchange-Q2")
                    .setHeader("header2", "HeaderForAllMatch")
                    .build();
            rabbitTemplate.send(headerExchangeName, "", message);
            return new TestResponse(person, "Success");
        }
    }

    class TestReturn implements Serializable {
        private String output = "Success";
    }
}
