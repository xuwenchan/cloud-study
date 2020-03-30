package com.rabbitmq.info;

import java.io.Serializable;

public class Messagee implements Serializable {

    private String id;
    private String messageBody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
