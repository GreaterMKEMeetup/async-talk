FROM openjdk:8-jre-alpine
EXPOSE 5050
COPY build/distributions/ratpack-docker.zip /opt/ratpack-docker/
RUN unzip /opt/ratpack-docker/ratpack-docker.zip -d /opt/ratpack-docker
WORKDIR /opt/ratpack-docker/ratpack-docker
CMD ["./bin/ratpack", "-fg"]