FROM openjdk:17-alpine
ARG JAR_FILE=target/HW3-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} aopapp.jar
ENTRYPOINT ["java","-jar","aopapp.jar"]