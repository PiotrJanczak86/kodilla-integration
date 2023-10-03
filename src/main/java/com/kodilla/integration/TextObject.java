package com.kodilla.integration;

import java.io.Serializable;

public class TextObject implements Serializable {
    private String name;
    private String body;

    public TextObject() {
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
