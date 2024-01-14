FROM maven:3.8-openjdk-17 AS builder

WORKDIR /

COPY pom.xml .
COPY src/ ./src/

#proiektua konpilatu maven-ekin
RUN mvn package -DskipTests

FROM openjdk:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/pelikulak-0.0.1-SNAPSHOT.jar .

#8080 portuan rest apia zabaltzen du
EXPOSE 8080

CMD ["java", "-jar", "pelikular-0.0.1-SNAPSHOT.jar"]