package com.gobbo.java_chat_app;

// POJO = Plain Old Java Object
public class Message {
    private String user;
    private String message;

    public Message() {}
    public Message(String user, String message) {
        this.message = message;
        this.user = user;
    }

    public String getUser() {
        return user;
    }
    public String getMessage() {
        return message;
    }

}
