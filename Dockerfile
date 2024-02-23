FROM openjdk:17-jdk-slim

WORKDIR /app

RUN echo $JAVA_HOME
RUN echo $MAVEN_HOME

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw package
COPY target/*.jar splitwizard.jar

RUN chmod +x splitwizard.jar

EXPOSE 8080

CMD ["splitwizard.jar"]