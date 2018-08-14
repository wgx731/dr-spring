#!/usr/bin/env bash

echo "[TEST-COVERAGE] source config.sh ..."
source ${PWD}/scripts/config.sh || exit 20

echo "[TEST-COVERAGE] generate coverage report ..."
${PWD}/mvnw clean verify || exit 21

echo "[TEST-COVERAGE] parse and show jacoco report:"
for module in ${MODULES}
do
    if [ -d "${PWD}/${module}/target/site" ]; then
        echo "  coverage report for ${module}: "
        awk -F\
        "," \
        '{ instructions += $4 + $5; covered += $5 } END \
        { print covered, "/", instructions, "instructions covered"; print 100*covered/instructions, "% covered" }' \
        ${PWD}/${module}/target/site/jacoco/jacoco.csv || exit 22
    fi
done
