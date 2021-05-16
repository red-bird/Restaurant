FROM gradle:7.0.2-jdk11 as build

USER root
# RUN   apk update \
#  &&   apk add ca-certificates wget \
#  &&   update-ca-certificates

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --stacktrace

FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/Restaurant-0.0.1-SNAPSHOT.jar Restaurant-0.0.1-SNAPSHOT.jar
COPY ./build/resources/main src/main/resources
ENTRYPOINT ["java", "-jar", "Restaurant-0.0.1-SNAPSHOT.jar"]