package com.apedano.rabbitmqspring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Person implements Serializable {
    private int ID;
    private String name;
}
