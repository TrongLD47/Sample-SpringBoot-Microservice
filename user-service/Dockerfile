FROM openjdk:11-alpine

WORKDIR /app

COPY target/user-service.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]

EXPOSE 9002