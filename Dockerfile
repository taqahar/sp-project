FROM maven:3.8.4 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

# Compile and package the application to an executable JAR
RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /open/app

# Construire l'application Spring Boot

COPY --from=maven /usr/src/app/target/supralog-project-0.0.1-SNAPSHOT.jar /open/app/

ENTRYPOINT ["java", "-jar", "supralog-project-0.0.1-SNAPSHOT.jar"]