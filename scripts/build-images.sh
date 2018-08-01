#!/usr/bin/env bash

echo "[BUILD-IMAGES] source config.sh ..."
source $PWD/scripts/config.sh || exit 60

echo "[BUILD-IMAGES] build image ..."
BASE_DIR=$PWD
cd ${BASE_DIR}/api-gateway
docker build \
  -t registry.heroku.com/dr-spring:${RELEASE_VERSION} \
  --build-arg JAR_FILE=api-gateway-${RELEASE_VERSION}.jar \
  -f Dockerfile.web . || exit 61
cd ${BASE_DIR}

echo "[BUILD-IMAGES] save image ..."
docker save \
  registry.heroku.com/dr-spring:${RELEASE_VERSION} |
  gzip -c > $PWD/target/dr-spring-${RELEASE_VERSION}.tar.gz || exit 62
