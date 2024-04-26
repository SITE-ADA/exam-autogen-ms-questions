# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17-slim as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Setup the runtime container
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/ms-questions-*.jar /ms-questions.jar
EXPOSE 9897
ENTRYPOINT ["java", "-jar", "/ms-questions.jar"]
