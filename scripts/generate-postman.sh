#!/usr/bin/env bash

echo "[DOCS - POSTMAN] source config.sh ..."
source $PWD/scripts/config.sh || exit 30

echo "[DOCS - POSTMAN] create postman directory ..."
mkdir -p $PWD/api-gateway/target/docs/postman || exit 31

echo "[DOCS - POSTMAN] generate postman collection json ..."
restdocs-to-postman \
  -i $PWD/api-gateway/target/docs/rest/snippets \
  -e postman \
  -f secondLastFolder \
  -o $PWD/api-gateway/target/docs/postman/collection.json || exit 32

echo "[DOCS - POSTMAN] show postman collection:"
cat $PWD/api-gateway/target/docs/postman/collection.json || exit 33
