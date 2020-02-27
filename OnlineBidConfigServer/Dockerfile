FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY src/main/resources/apiEncryptionKey.jks apiEncryptionKey.jks
# copy the local security library (of the EC2 machine) to the container
COPY UnlimitedJCEPolicyJDK8/* /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/
COPY target/photo-app-api-config-server-0.0.1-SNAPSHOT.jar ConfigServer.jar
# argument that make java to generate random number slightly faster
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","ConfigServer.jar"]