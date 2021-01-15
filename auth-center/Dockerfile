FROM openjdk:8-jre
MAINTAINER zhb1nk
ENV SPRING_PROFILE_ACTIVE=docker
COPY target/auth-center-1.0-SNAPSHOT.jar /auth-center.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","/auth-center.jar"]