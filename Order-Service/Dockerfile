# Use the official Java 21 image from OpenJDK
FROM openjdk:21-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and the pom.xml file
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .

# Copy the source code into the container
COPY src ./src

# Build the application using Maven (using the wrapper)
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Create a new stage to copy only the compiled JAR
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage into the container
COPY --from=build /app/target/order-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
