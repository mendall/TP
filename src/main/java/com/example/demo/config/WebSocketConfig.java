package com.example.demo.config;

import com.example.demo.websocket.UserWebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * The WebSocketConfig class is responsible for configuring the WebSocket functionality in the application.
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public UserWebSocket userWebSocket() {
        return new UserWebSocket();
    }
}