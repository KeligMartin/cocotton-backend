# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/cocotton-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/cocotton-backend.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/usr/local/lib/cocotton-backend.jar"]
