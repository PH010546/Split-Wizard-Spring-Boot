package com.splitwizard.splitwizard.socket;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private MessageType type;
    private String message;
    private Object data;

    public Message(MessageType type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }
}
