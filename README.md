# Flower shop application
__________________________________

This is a flower shop website for software engineering 2 classes.

## Prerequisites
___

- Java 17
- Docker desktop (in the future/nice to have)

## Build & Run
___
In order to build the project, please change the working directory
to ``/flower-shop-se2``.

To build the project, run the following command
```
./gradlew build
```
To run the project, run the following command
```
./java -jar /build/libs/flower-shop-1.0-SNAPSHOT.jar
```

To run the project using different database host (default is localhost) run the following command
```
./java -jar /build/libs/flower-shop-1.0-SNAPSHOT.jar --flower-shop-db={DATABASE_HOST}
```

## Swagger
___
To project has swagger configured. In order to open it, run the application and enter the link below

`http://localhost:8080/swagger-ui/index.html`



## Technological stack
___
- Java 17
- Springboot 2
- PostgreSQL
- React
- Tailwind