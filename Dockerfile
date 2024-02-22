FROM openjdk:17-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw clean install

COPY ./target/splitwizard-0.1.jar .

RUN java -jar splitwizard-0.1.jar

EXPOSE 8080

CMD ["java", "-jar", "splitwizard-0.1.jar"]