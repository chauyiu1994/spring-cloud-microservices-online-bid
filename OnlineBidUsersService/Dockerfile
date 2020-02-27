FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/photo-app-api-users-0.0.1-SNAPSHOT.jar users-microservice.jar
ENTRYPOINT ["java","-jar","users-microservice.jar"]