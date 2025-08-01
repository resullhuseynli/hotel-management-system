FROM openjdk:17-jdk-slim
WORKDIR /project
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]