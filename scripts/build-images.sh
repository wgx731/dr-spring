#!/usr/bin/env bash

echo "[BUILD-IMAGES]: precondition list"
echo "1. 'docker', 'docker-compose' and 'gzip' installed on system."
echo "2. following config files must be in '$PWD/target/config': "
echo "
    dr-spring-dubbo-provider.properties
    dr-spring-gateway.properties
    grpc.env
"
echo "3. following environment variables must be present: "
echo "
    DR_SPRING_VERSION

    DUBBO_WEB_H_PORT
    DUBBO_WEB_C_PORT
    DUBBO_MM_H_PORT
    DUBBO_MM_C_PORT
    DUBBO_APP_H_PORT
    DUBBO_APP_C_PORT
    DUBBO_QOS_H_PORT
    DUBBO_QOS_C_PORT

    GRPC_APP_H_PORT
    GRPC_APP_C_PORT

    GATEWAY_WEB_H_PORT
    GATEWAY_WEB_C_PORT
    GATEWAY_MM_H_PORT
    GATEWAY_MM_C_PORT
"

echo "[BUILD-IMAGES] source config.sh ..."
source ${PWD}/scripts/config.sh || exit 50

echo "[BUILD-IMAGES] build image ..."
BASE_DIR=${PWD}
cd ${BASE_DIR}/target || exit 51
cp -R ${BASE_DIR}/scripts/docker/* ./ || exit 51
cp -R ${BASE_DIR}/local/config.sample ./config || exit 51
source ${BASE_DIR}/local/ports.sh || exit 51
docker-compose build || exit 51
cd ${BASE_DIR} || exit 51

echo "[BUILD-IMAGES] save image ..."
mkdir -p ${PWD}/target/tars || exit 52
for module in ${DOCKER_MODULES}
do
    echo "  saving ${module} image ..."
    docker save \
        ${module} |
        gzip -c > ${PWD}/target/tars/${module}-${RELEASE_VERSION}.tar.gz || exit 52
done
ls -lah ${PWD}/target/tars || exit 52
