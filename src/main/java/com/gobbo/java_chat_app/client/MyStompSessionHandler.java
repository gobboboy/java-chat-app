package com.gobbo.java_chat_app.client;

import com.gobbo.java_chat_app.Message;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler  extends StompSessionHandlerAdapter {
    private String username;

    public  MyStompSessionHandler(String username) {
        this.username = username;
    }

    // here is where the websocket will broadcast all messages
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Client Connected");
        session.send("/app/connect", username);

        session.subscribe("/topic/messages", new StompFrameHandler() {

            // method to inform our client which class we expect to receive to convert data to obj
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            // method to check if the payload is a message and if it is transform the data into message obj
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                try {
                    if (payload instanceof Message) {
                        Message message = (Message) payload;
                        System.out.println("Received message: " + message.getUser() + ": " + message.getMessage());
                    } else {
                        System.out.println("Received Unexpected payload type: " + payload.getClass());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Client Subscribe to /topic/messages");
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        exception.printStackTrace();
    }
}
