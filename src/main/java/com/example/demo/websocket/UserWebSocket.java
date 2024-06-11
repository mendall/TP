package com.example.demo.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket/user")
public class UserWebSocket {

    /**
     * Method called when a new WebSocket connection is opened.
     *
     * @param session The session associated with the connection.
     * @throws IOException If an I/O error occurs while sending the initial message to the client.
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        session.getBasicRemote().sendText("Connection Established");
    }

    /**
     * Callback method called when a new message is received from the WebSocket connection.
     *
     * @param message The message received from the client.
     * @param session The session associated with the connection.
     * @throws IOException If an I/O error occurs while sending a response message to the client.
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // Handle the received message
        session.getBasicRemote().sendText("Received: " + message);
    }

    /**
     * Callback method called when the WebSocket connection is closed.
     *
     * @param session The session associated with the connection.
     * @throws IOException If an I/O error occurs while sending the closing message to the client.
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        session.getBasicRemote().sendText("Connection Closed");
        session.close();
    }

    /**
     * Callback method called when an error occurs in the WebSocket connection.
     *
     * @param session   The session associated with the connection.
     * @param throwable The exception that caused the error.
     * @throws IOException If an I/O error occurs while handling the error.
     */
    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        session.close();
        throwable.printStackTrace();
    }
}