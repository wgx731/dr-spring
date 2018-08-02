#!/usr/bin/env bash

echo "[DOCS - POSTMAN] source config.sh ..."
source $PWD/scripts/config.sh || exit 30

echo "[DOCS - POSTMAN] generate postman collection json ..."
restdocs-to-postman \
  -i $PWD/dr-spring-api/target/docs/rest/snippets \
  -e postman \
  -f secondLastFolder \
  -o $PWD/dr-spring-api/target/docs/postman/collection.json || exit 31

echo "[DOCS - POSTMAN] show postman collection:"
cat $PWD/dr-spring-api/target/docs/postman/collection.json || exit 32
