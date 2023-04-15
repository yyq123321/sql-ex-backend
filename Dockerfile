FROM maven:3.8-eclipse-temurin-17-alpine

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn clean package -Dmaven.compiler.source=17 -Dmaven.compiler.target=17 -DskipTests

# Run the web service on container startup.
CMD ["java","-jar","/app/target/user-center-backend-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
