package com.splitwizard.splitwizard.socket;

import lombok.Data;

import java.util.List;

@Data
public class Message {

    private String senderId;
    private List<String> targetUserIds;
    private Object message;

}
