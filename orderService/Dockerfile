FROM openjdk:11
COPY target/artifact-0.0.1-SNAPSHOT.jar  /usr/src/app/service.jar
WORKDIR /usr/src/app
EXPOSE 8083
CMD ["java", "-jar" , "service.jar"]