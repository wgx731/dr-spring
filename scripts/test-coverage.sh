#!/usr/bin/env bash

echo "[TEST-COVERAGE] source config.sh ..."
source ${PWD}/scripts/config.sh || exit 20

echo "[TEST-COVERAGE] count source code lines ..."
echo "total lines: $(find . -name "*.java" | xargs cat | grep "[a-zA-Z0-9{}]" | wc -l | tr -d ' ')"
for module in ${MODULES}
do
  echo "${module} source code lines: $(find ./${module}/src/main -name "*.java" \
    | xargs cat | grep "[a-zA-Z0-9{}]" | wc -l | tr -d ' ')"
  echo "${module} test code lines: $(find ./${module}/src/test -name "*.java" \
    | xargs cat | grep "[a-zA-Z0-9{}]" | wc -l | tr -d ' ')"
done

echo "[TEST-COVERAGE] generate coverage report ..."
${PWD}/mvnw -q clean verify || exit 21

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
