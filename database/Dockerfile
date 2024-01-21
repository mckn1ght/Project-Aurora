# Use the official MySQL Docker image
FROM mysql:8.0

# Set MySQL environment variables
ENV MYSQL_ROOT_PASSWORD=Admin12!@
ENV MYSQL_DATABASE=cloudComputing

# Copy the initialization script into the container
COPY init.sql /docker-entrypoint-initdb.d/
