FROM openjdk:17-oracle
ADD /target/Notes2-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]