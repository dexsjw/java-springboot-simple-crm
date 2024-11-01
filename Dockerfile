# FROM eclipse-temurin:17-jdk-jammy
# WORKDIR /app
# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./
# COPY src ./src
# # EXPOSE 8080
# RUN ./mvnw clean install -DskipTests
# CMD ["./mvnw", "spring-boot:run"]

FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . ./
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /app/target/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]