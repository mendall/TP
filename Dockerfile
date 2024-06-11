# Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim

# The application's jar file
ARG JAR_FILE=target/demo-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} demo.jar

# Add the MySQL script
ADD data.sql data.sql

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo.jar"]