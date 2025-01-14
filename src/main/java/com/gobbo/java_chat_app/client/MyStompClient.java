package com.gobbo.java_chat_app.client;

import com.gobbo.java_chat_app.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyStompClient {
    // Adding StompSession for send messages, subscribe to routes and manage the connection
    private StompSession session;
    private String username;

    public MyStompClient(MessageListener messageListener, String username) throws ExecutionException, InterruptedException {
        this.username = username;

        // Transport is used to transfer data between client and server
        // to ensure the websocket to communicate properly with the server
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        SockJsClient sockJsClient = new SockJsClient(transports);

        // Implementing STOMP protocol to interact with our websocket
        // We will need to serialize or format our data such that our websocket is able to accept it
        // And also need to deserialize or interpret incoming data from websocket to the client code
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler(messageListener, username);
        String url = "ws://localhost:8080/ws"; // Use ws:// for websocket

        session = stompClient.connectAsync(url, sessionHandler).get();
    }

    public void sendMessage(Message message) {
        try {
            // session takes 2 parameters, destination and the message
            session.send("/app/message", message);
            System.out.println(message.getUser() + ": " + message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectUser(String username) {
        session.send("/app/disconnect", username);
        System.out.println("Disconnect User: " + username);
    }
}
