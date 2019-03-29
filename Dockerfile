FROM openjdk:8-alpine
COPY ./build/libs/*.jar /usr/src/assign-time
WORKDIR /usr/src/assign-time
EXPOSE 4941
ENTRYPOINT ["java", "-jar", "AssignTime.jar"]