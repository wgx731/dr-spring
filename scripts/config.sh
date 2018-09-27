#!/usr/bin/env bash

export RELEASE_VERSION="1.3.6"
export SNAPSHOT_VERSION="1.3.7-SNAPSHOT"
export TARGET_URL="http://$DEV_IP:8080"
export DOCKER_MODULES="dr-spring-dubbo-provider dr-spring-grpc-provider dr-spring-gateway"
export MODULES="dr-spring-common $DOCKER_MODULES"
export OS=`uname`
