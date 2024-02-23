FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw clean install -DskipTests -e
COPY ./target/*.jar splitwizard.jar

RUN chmod +x splitwizard.jar

EXPOSE 8080

CMD ["splitwizard.jar"]