#!/usr/bin/env bash

echo "[BUILD-ARTIFACTS] source config.sh ..."
source ${PWD}/scripts/config.sh || exit 20

echo "[BUILD-ARTIFACTS] building artifacts ..."
${PWD}/mvnw clean verify package || exit 21

echo "[BUILD-ARTIFACTS] packaging artifacts ..."
for module in ${MODULES}
do
    mkdir -p ${PWD}/target/jars || exit 22
    if [ -f ${PWD}/${module}/target/${module}-*-exec.jar ]; then
        cp ${PWD}/${module}/target/${module}-*-exec.jar \
            ${PWD}/target/jars || exit 22
    fi
    if [ -d "${PWD}/${module}/target/site" ]; then
      mkdir -p ${PWD}/target/site/${module} || exit 22
      cp -R ${PWD}/${module}/target/site/* \
        ${PWD}/target/site/${module} || exit 22
     fi
done
