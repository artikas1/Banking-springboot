#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests

#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/Banking-springboot-0.0.1-SNAPSHOT.jar Banking-springboot.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "./Banking-springboot.jar"]

FROM openjdk:23-jdk
COPY target/Banking-springboot-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

#FROM eclipse-temurin:21-jdk-alpine
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:21-jdk
#VOLUME /tmp
#COPY target/Banking-springboot-0.0.1-SNAPSHOT.jar /app/app.jar
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]
