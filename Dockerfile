# Build stage
FROM maven:3.9.4-eclipse-temurin-17 as builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/appointment-system-api-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
