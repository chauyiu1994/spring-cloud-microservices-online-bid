FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/photoAppApiZuulApiGateway-0.0.1-SNAPSHOT.jar ZuulApiGateway.jar
ENTRYPOINT ["java","-jar","ZuulApiGateway.jar"]