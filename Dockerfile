FROM openjdk:8-jdk-alpine
VOLUME /tmp
WORKDIR /case-management
EXPOSE 8081
ADD target/WebApplication-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]