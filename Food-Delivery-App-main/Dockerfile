# ======================
# 1. Build stage
# ======================
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies first (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ======================
# 2. Runtime stage
# ======================
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/Foody-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render sets PORT env var automatically)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
