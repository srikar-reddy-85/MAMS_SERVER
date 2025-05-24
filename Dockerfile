#FROM openjdk:18-jdk-slim
#
#COPY target/mams-backend-0.0.1-SNAPSHOT.jar mams-backend-0.0.1.jar
#
#ENTRYPOINT ["java", "-jar", "mams-backend-0.0.1.jar"]

# Use official OpenJDK 18 runtime as base image
#FROM openjdk:18-jdk-slim
#
## Set working directory
#WORKDIR /app
#
## Copy Maven wrapper files
#COPY mvnw .
#COPY .mvn .mvn
#
## Copy pom.xml
#COPY pom.xml .
#
## Download dependencies (this layer will be cached if pom.xml doesn't change)
#RUN ./mvnw dependency:go-offline -B
#
## Copy source code
#COPY src src
#
## Build the application
#RUN ./mvnw clean package -DskipTests
#
## Expose port
#EXPOSE 8080
#
## Run the application
#CMD ["java", "-jar", "target/*.jar"]

# Use official OpenJDK 18 runtime as base image
FROM openjdk:18-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Copy pom.xml
COPY pom.xml .

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Find and rename the jar file to a consistent name
RUN mv target/*.jar target/app.jar

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/app.jar"]