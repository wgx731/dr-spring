#!/bin/bash

RED='\033[0;31m'
NC='\033[0m'

function setAppVer {
    for i in `find . -name "application.properties"`; do
        sed -i "" "s/^info.app.version=.*/info.app.version=$1/g" $i
        grep -q "^info.app.version=.*" $i || echo "info.app.version=$1" >> $i
    done
}

newVersion=$1
nextVersion=$2

echo -e "${RED}Setting release version $newVersion${NC}"

git checkout master
git pull
git merge origin/dev
./mvnw versions:set -DnewVersion=$newVersion
sed -i.bak "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$newVersion<\/dr-spring.version>|" pom.xml
setAppVer $newVersion
git add pom.xml */pom.xml */application.properties
git commit -m "[release]: set release version $newVersion :octopus:"
git push
git tag v$newVersion
git push origin v$newVersion

echo -e "${RED}Setting snapshot version $nextVersion${NC}"

git checkout master
./mvnw versions:set -DnewVersion=$nextVersion
sed -i.bak "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$nextVersion<\/dr-spring.version>|" pom.xml
setAppVer $nextVersion
git add pom.xml */pom.xml */application.properties
git commit -m "[release]: set snapshot version $nextVersion :snake:"
git push
git checkout dev
git pull
git merge master
git push

echo -e "${RED}Cleaning Up${NC}"

rm -rf pom.xml.* */pom.xml.*

