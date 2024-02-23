FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw package
COPY target/*.jar splitwizard.jar

RUN chmod +x splitwizard.jar

EXPOSE 8080

CMD ["splitwizard.jar"]