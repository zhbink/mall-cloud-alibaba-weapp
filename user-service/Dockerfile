FROM openjdk:8-jre
MAINTAINER zhb1nk
ENV SPRING_PROFILE_ACTIVE=docker
COPY target/user-service-1.0-SNAPSHOT.jar /user-service.jar
EXPOSE 8003
ENTRYPOINT ["java","-jar","/user-service.jar"]