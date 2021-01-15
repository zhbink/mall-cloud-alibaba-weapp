FROM openjdk:8-jre
MAINTAINER zhb1nk
EXPOSE 8858
COPY sentinel-dashboard-1.8.0.jar /sentinel-dashboard.jar
ENTRYPOINT ["java","-Dserver.port=8858","-Dcsp.sentinel.dashboard.server=localhost:8858","-jar","/sentinel-dashboard.jar"]