FROM openjdk:11-jre-slim
ADD build/libs/rest-jwt-example.jar rest-jwt-example.jar
ENTRYPOINT ["java", "-jar", "rest-jwt-example.jar"]