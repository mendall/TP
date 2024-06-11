# Test Project

## Overview

This project is a Spring Boot application that demonstrates the integration of a MySQL database, WebSockets, Kafka, and Docker. The application includes REST endpoints for user management, WebSocket communication, and Kafka messaging.

## Features

- **User Management**: CRUD operations for users via REST API.
- **WebSockets**: Real-time communication using WebSockets.
- **Kafka**: Messaging with Kafka for producing and consuming messages.
- **Docker**: Docker configuration to run the application, MySQL, Kafka, and Zookeeper.
- **Logging**: Request logging using a Spring Interceptor.
- **Testing**: Unit tests with JUnit and BDD tests with Cucumber.

## Prerequisites

- Docker
- Docker Compose
- Maven
- Java 11 or higher

## Running the Application

1. **Build the application**:

   ```sh
   mvn clean package
   
2. **Run the application with Docker Compose**:
   ```sh 
   docker-compose up --build

3. **Access the application**:

    * REST API: http://localhost:8080
    * WebSocket endpoint: ws://localhost:8080/websocket/user

## Running the Tests

1. **Unit Tests**:

    ```sh
    mvn test

2. **Cucumber Tests**:

    ```sh
    mvn test -Dcucumber

## API Endpoints

Authorization:

      username: admin
      password: password

* GET /public/users: Retrieve all users
* GET /public/users/{id}: Retrieve a user by ID
* POST /private/users: Create a new user
* PUT /private/users: Update a user by ID
* DELETE /private/users: Delete users by IDs
* GET /private/users/{name}: Retrieve a user by name


## WebSocket Usage
* Connect to ws://localhost:8080/websocket/user
* Send a message to the WebSocket to receive a response.

## Kafka Usage
* Producer: Use KafkaProducerService to send messages to the users topic.
* Consumer: Messages from the users topic are consumed by KafkaConsumerService.