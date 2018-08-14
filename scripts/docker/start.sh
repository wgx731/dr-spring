#!/usr/bin/env bash

if [ ! -f /runner/config/application.properties ]; then
    echo "Missing application.properties file."
    exit 2
fi

java \
    ${JAVA_OPTS} \
    -Djava.security.egd=file:/dev/./urandom \
    -jar app.jar \
    ${SPRING_OPTS} \
    --spring.config.location=file:/runner/config/application.properties
