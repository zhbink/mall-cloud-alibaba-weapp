FROM openjdk:8-jre
MAINTAINER zhb1nk
ENV SPRING_PROFILE_ACTIVE=docker
COPY target/goods-service-1.0-SNAPSHOT.jar /goods-service.jar
ENTRYPOINT ["java","-jar","/goods-service.jar"]