FROM openjdk:11
COPY target/artifact-0.0.1-SNAPSHOT.jar  /usr/src/app/service.jar
WORKDIR /usr/src/app
EXPOSE 8081
CMD ["java", "-jar" , "service.jar"]