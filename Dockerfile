#Docker run Build Run JAR

# the first stage of our build will use a maven 3.8.3 parent image
FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD
# copy the pom and src code to the container
COPY ./ ./
# package our application code
RUN mvn clean package
# the second stage of our build will use open jdk 17 oracle
FROM openjdk:17-oracle
# copy only the artifacts we need from the first scau7y856tage and discard the rest
COPY --from=MAVEN_BUILD /target/*.jar /back-end-api.jar
# set the startup command to execute the jar
CMD ["java", "-jar", "/back-end-api.jar"]
