FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY ./pom.xml pom.xml
COPY ./src src/
RUN mvn clean package -B -e

FROM openjdk:8-jre-alpine
COPY --from=MAVEN_BUILD target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]