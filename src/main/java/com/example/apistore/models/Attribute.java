package com.example.apistore.models;

import lombok.Data;

@Data
public class Attribute {
    private String name;
    private String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
