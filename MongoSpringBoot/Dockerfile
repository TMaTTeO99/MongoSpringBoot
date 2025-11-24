FROM eclipse-temurin:21-jdk-alpine

VOLUME /tmp

COPY app-boot/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]