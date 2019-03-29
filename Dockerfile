FROM openjdk:8-alpine
EXPOSE 4941

COPY . /usr/src/assign-time
WORKDIR /usr/src/assign-time

RUN "ls"

ENTRYPOINT ["java", "-jar", "AssignTime.jar"]