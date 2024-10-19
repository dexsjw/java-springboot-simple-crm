# FROM eclipse-temurin:17-jdk-jammy
# WORKDIR /app
# # COPY .mvn/ .mvn
# # COPY mvnw pom.xml ./
# # COPY src ./src
# COPY . .
# RUN chmod +x ./mvnw
# RUN ./mvnw.cmd install -DskipTests
# CMD ["./mvnw", "spring-boot:run"]

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
RUN chmod 774 ./mvnw
RUN ./mvnw clean install -DskipTests
CMD ["./mvnw", "spring-boot:run"]