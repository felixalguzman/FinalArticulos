FROM openjdk:8-jdk-alpine

LABEL maintainer="Ansel Corona <anselcorona@gmail.com>"

EXPOSE 8080

COPY articulos-0.0.1-SNAPSHOT.jar finalarticulos.jar

ENTRYPOINT ["java", "-jar", "finalarticulos.jar"]
