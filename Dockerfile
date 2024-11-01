FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
# EXPOSE 8080
RUN ./mvnw clean install -DskipTests
CMD ["./mvnw", "spring-boot:run"]