USE openjdk:8-alpine
EXPOSE 4941

COPY . /usr/src/assign-time
WORKDIR /usr/src/assign-time


ENTRYPOINT ["java", "-jar", ""]