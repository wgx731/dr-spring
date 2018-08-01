#!/usr/bin/env bash

echo "[BUILD-ARTIFACTS] source config.sh ..."
source $PWD/scripts/config.sh || exit 20

echo "[BUILD-ARTIFACTS] building artifacts ..."
$PWD/mvnw clean package || exit 21

echo "[BUILD-ARTIFACTS] packaging artifacts ..."
cp $PWD/api-gateway/target/*.jar \
  $PWD/target || exit 22
for module in ${MODULES}
do
    if [ -d "$PWD/${module}/target/site" ]; then
      mkdir -p $PWD/target/site/${module} || exit 22
      cp -R $PWD/${module}/target/site/* \
        $PWD/target/site/${module} || exit 22
     fi
done