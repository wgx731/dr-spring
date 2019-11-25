FROM openjdk:8-jdk-slim
MAINTAINER Wang Eric GaoXiang <wgx731@gmail.com>

# create runner user and runner directory
RUN useradd -d /runner -ms /bin/bash runner && \
    mkdir -p /runner/config

# add app jar file
ARG JAR_FILE
ADD ${JAR_FILE} /runner/app.jar
ARG START_FILE
ADD ${START_FILE} /runner/start.sh

# change current user
USER runner

# change working directory
WORKDIR /runner

# start app
CMD ${PWD}/start.sh
