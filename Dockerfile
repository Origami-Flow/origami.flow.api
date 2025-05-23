FROM maven:3-openjdk-17 AS builder

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /build/target/*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]