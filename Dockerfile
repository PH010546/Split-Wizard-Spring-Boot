FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw clean install

COPY target/*.jar splitwizard.jar

RUN java -jar splitwizard.jar

EXPOSE 8080

CMD ["java", "-jar", "splitwizard.jar"]