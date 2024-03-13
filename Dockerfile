FROM openjdk:17-jdk-slim

ADD target/github-action.jar github-action.jar

ENTRYPOINT ["java", "-jar","github-action.jar"]