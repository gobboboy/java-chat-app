package com.gobbo.java_chat_app;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Enabling applications to comunicate w/ each other
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    // basically client sends msg to /app and receives msg from /topic
    // messageBroker will do the intermediation
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    // STOMP = Simple Text Oriented Message Protocol
    // withSockJS() method to ensure that the websocket will run in every browser
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }
}
