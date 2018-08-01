#!/usr/bin/env bash

function setAppVer {
    for i in `find . -name "application.properties"`; do
        sed -i "" "s/^info.app.version=.*/info.app.version=$1/g" ${i}
        grep -q "^info.app.version=.*" ${i} || echo "info.app.version=$1" >> ${i}
    done
}

echo "[MAKE-RELEASE] source config.sh ..."
source $PWD/scripts/config.sh || exit 50

echo "[MAKE-RELEASE] creating release with version $RELEASE_VERSION in 'master' branch ..."
git checkout master || exit 51
git pull || exit 51
git merge origin/dev || exit 51
$PWD/mvnw versions:set -DnewVersion=${RELEASE_VERSION} || exit 51
sed \
 -i "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$RELEASE_VERSION<\/dr-spring.version>|" \
 pom.xml || exit 51
setAppVer ${RELEASE_VERSION} || exit 51
git add pom.xml */pom.xml */application.properties || exit 51
git commit -m "[RELEASE]: create release with version $RELEASE_VERSION in 'master' branch :octopus:" || exit 51
git push || exit 51
git tag v${RELEASE_VERSION} || exit 51
git push origin v${RELEASE_VERSION} || exit 51

echo "[MAKE-RELEASE] creating snapshot with version $SNAPSHOT_VERSION in 'dev' branch ..."
git checkout dev || exit 52
$PWD/mvnw versions:set -DnewVersion=${SNAPSHOT_VERSION} || exit 52
sed \
  -i "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$SNAPSHOT_VERSION<\/dr-spring.version>|" \
  pom.xml || exit 52
setAppVer ${SNAPSHOT_VERSION} || exit 52
git add pom.xml */pom.xml */application.properties || exit 52
git commit -m "[RELEASE]: create snapshot with version $SNAPSHOT_VERSION in 'dev' branch :snake:" || exit 52
git push || exit 52

echo "[MAKE-RELEASE] cleaning up temp files ..."
rm -rf pom.xml.* */pom.xml.* || exit 53
