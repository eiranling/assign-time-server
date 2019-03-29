FROM openjdk:8-alpine
EXPOSE 4941

COPY ./builds/libs/*.jar /usr/src/assign-time
WORKDIR /usr/src/assign-time

RUN "ls"

ENTRYPOINT ["java", "-jar", "AssignTime.jar"]