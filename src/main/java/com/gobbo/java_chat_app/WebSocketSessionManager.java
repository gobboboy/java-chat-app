package com.gobbo.java_chat_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// service annotation indicates that this class is a spring service component
@Service
public class WebSocketSessionManager {
    private final ArrayList<String> activeUsernames = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate;

    // same message template that we are using on our controller class
    @Autowired WebSocketSessionManager(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void addUsername(String username) {
        activeUsernames.add(username);
    }
    public void removeUsername(String username) {
        activeUsernames.remove(username);
    }

    public void broadcastActiveUsernames() {
        messagingTemplate.convertAndSend("/topic/users", activeUsernames);
        System.out.println("Broadcasting active users to /topic/users " + activeUsernames);
    }
}
