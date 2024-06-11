FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD data.sql data.sql
COPY target/demo-1.0-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","/demo.jar"]
