FROM openjdk:11-alpine

WORKDIR /app

COPY target/customer-service.jar customer-service.jar

ENTRYPOINT ["java", "-jar", "customer-service.jar"]

EXPOSE 9006