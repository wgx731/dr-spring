#!/usr/bin/env bash

function setAppVer {
    for i in `find . -name "application.properties"`; do
        sed -i "" "s/^info.app.version=.*/info.app.version=$1/g" ${i}
        grep -q "^info.app.version=.*" ${i} || echo "info.app.version=$1" >> ${i}
    done
}

echo "[MAKE-RELEASE] source config.sh ..."
source $PWD/scripts/config.sh || exit 60

echo "[MAKE-RELEASE] creating release with version $RELEASE_VERSION in 'master' branch ..."
git checkout master || exit 61
git pull || exit 61
git merge origin/dev || exit 61
$PWD/mvnw versions:set -DnewVersion=${RELEASE_VERSION} || exit 61
sed \
 -i "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$RELEASE_VERSION<\/dr-spring.version>|" \
 pom.xml || exit 61
setAppVer ${RELEASE_VERSION} || exit 61
git add pom.xml */pom.xml */application.properties || exit 61
git commit -m "[RELEASE]: create release with version $RELEASE_VERSION in 'master' branch :octopus:" || exit 61
git push || exit 61
git tag v${RELEASE_VERSION} || exit 61
git push origin v${RELEASE_VERSION} || exit 61

echo "[MAKE-RELEASE] creating snapshot with version $SNAPSHOT_VERSION in 'dev' branch ..."
git checkout dev || exit 62
$PWD/mvnw versions:set -DnewVersion=${SNAPSHOT_VERSION} || exit 62
sed \
  -i "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$SNAPSHOT_VERSION<\/dr-spring.version>|" \
  pom.xml || exit 62
setAppVer ${SNAPSHOT_VERSION} || exit 62
git add pom.xml */pom.xml */application.properties || exit 62
git commit -m "[RELEASE]: create snapshot with version $SNAPSHOT_VERSION in 'dev' branch :snake:" || exit 62
git push || exit 62

echo "[MAKE-RELEASE] cleaning up temp files ..."
rm -rf pom.xml.* */pom.xml.* || exit 63
