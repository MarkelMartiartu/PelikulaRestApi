FROM maven:3.9.6-amazoncorretto-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src/ ./src/

#proiektua konpilatu maven-ekin
RUN mvn package -DskipTests

FROM amazoncorretto:21-alpine3.17

WORKDIR /app

COPY --from=builder /app/target/pelikularest-0.0.1-SNAPSHOT.jar .

#8080 portuan rest apia zabaltzen du
EXPOSE 8080

CMD ["java", "-jar", "pelikularest-0.0.1-SNAPSHOT.jar"]