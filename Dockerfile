# POWERED BY FURKAN OZMEN
FROM amazoncorretto:11-alpine-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ecommerce-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ecommerce-0.0.1-SNAPSHOT.jar"]