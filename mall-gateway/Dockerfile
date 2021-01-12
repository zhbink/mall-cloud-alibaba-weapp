FROM openjdk:8-jre
MAINTAINER zhb1nk

ENV SPRING_PROFILE_ACTIVE=docker

COPY target/mall-gateway-1.0-SNAPSHOT.jar /gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/gateway.jar"]