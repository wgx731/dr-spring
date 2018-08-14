#!/usr/bin/env bash

echo "[DOCS - OPEN API] source config.sh ..."
source ${PWD}/scripts/config.sh || exit 40

echo "[DOCS - OPEN API] generate open gateway yaml file ..."
apimatic-cli transform \
  fromauthkey --auth-key ${APIMATIC_KEY} \
  --download-to ${PWD}/dr-spring-gateway/target/docs/openapi \
  --file ${PWD}/dr-spring-gateway/target/docs/postman/collection.json \
  --format SwaggerYaml || exit 41
mv ${PWD}/dr-spring-gateway/target/docs/openapi/converted.yaml \
  ${PWD}/dr-spring-gateway/target/docs/openapi/description.yaml || exit 41

echo "[DOCS - OPEN API] show open gateway yaml file:"
cat ${PWD}/dr-spring-gateway/target/docs/openapi/description.yaml || exit 42
