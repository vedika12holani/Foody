# Dockerfile (place next to pom.xml)

# 1) Build stage
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom and optional mvnw so wrapper works if present
COPY pom.xml .
COPY mvnw . || true
COPY .mvn .mvn || true
RUN mvn dependency:go-offline -B

# copy source and build jar
COPY src ./src
RUN mvn -B clean package -DskipTests

# 2) Runtime stage
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# copy the produced jar (use wildcard to avoid name mismatches)
COPY --from=build /app/target/*.jar app.jar

# Optional memory limits (adjust if needed)
ENV JAVA_TOOL_OPTIONS="-Xms256m -Xmx512m"

# Expose port (app should read ${PORT})
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_TOOL_OPTIONS -jar /app/app.jar"]
