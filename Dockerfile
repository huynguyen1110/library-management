FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/code-0.0.1.jar app.jar

# Define the entry point to start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose the port on which the Spring Boot application runs
EXPOSE 8888
