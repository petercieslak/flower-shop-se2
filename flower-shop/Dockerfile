FROM openjdk:17-jdk-alpine
MAINTAINER piotr.m.cieslak@gmail.com
COPY /build/libs/flower-shop-1.0-SNAPSHOT.jar flower-shop-1.0.jar
ENTRYPOINT ["java","-jar","/flower-shop-1.0.jar", "--flower-shop-db='flower-shop-db'"]
