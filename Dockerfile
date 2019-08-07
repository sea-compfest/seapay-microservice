FROM openjdk:8

WORKDIR /opt/seapay
VOLUME /opt/seapay
EXPOSE 8080

RUN apt-get update
RUN apt-get install -y build-essential postgresql-client vim
