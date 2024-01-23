# Use the multi-architecture AdoptOpenJDK image
FROM openjdk:17-oracle

# Set the working directory to /back
WORKDIR /back/cloudComputing

# Copy the project files into the container
COPY . .

# Build the application
RUN ./back/cloudComputing/mvnw -f "./back/cloudComputing/pom.xml" clean install -DskipTests

# Expose the port on which the application runs
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "./back/cloudComputing/target/cloudComputing-0.1.5-SNAPSHOT.jar"]
