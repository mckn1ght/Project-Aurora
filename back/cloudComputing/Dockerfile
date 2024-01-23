# Use the multi-architecture AdoptOpenJDK image
FROM openjdk:17-oracle

# Set the working directory to /back
WORKDIR /back/cloudComputing

# Copy only the necessary files into the container
COPY .mvn .mvn
COPY pom.xml .

# Copy the rest of the project files
COPY src src

# Copy the mvnw script to the expected location
COPY mvnw .

# Set execute permission for the mvnw script
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean install -DskipTests

# Expose the port on which the application runs
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "target/cloudComputing-0.1.5-SNAPSHOT.jar"]
