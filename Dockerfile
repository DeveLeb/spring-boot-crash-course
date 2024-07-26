FROM openjdk:17-jdk-alpine
MAINTAINER develeb.org
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]