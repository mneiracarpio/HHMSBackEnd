FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
MAINTAINER mn
EXPOSE 9090
COPY target/backendhhms-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
