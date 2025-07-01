# Base image with Java
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy pre-built Spring Boot JAR
COPY target/D387_sample_code-0.0.2-SNAPSHOT.jar /app/app.jar

# Expose the back end port
EXPOSE 8080

# Start the Spring Boot app
CMD ["java", "-jar", "app.jar"]
