# Use the multi-architecture AdoptOpenJDK image
FROM openjdk:17-oracle

# Set the working directory to /back
WORKDIR /back

# Copy only the necessary files into the container
COPY mvnw .mvn .mvn
COPY pom.xml .

# Copy the rest of the project files
COPY src src

# Copy the mvnw script to the expected location
COPY mvnw .

# Build the application
RUN ./mvnw clean install -DskipTests

# Expose the port on which the application runs
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "target/cloudComputing-0.1.5-SNAPSHOT.jar"]
