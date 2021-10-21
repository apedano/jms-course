package com.apedano.rabbitmqspring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class TestResponse {
    private Person person;
    private String message;

}
