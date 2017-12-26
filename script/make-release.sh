#!/bin/bash

function setAppVer {
    for i in `find . -name "application.properties"`; do
        sed -i "" "s/^info.app.version=.*/info.app.version=$1/g" $i
        grep -q "^info.app.version=.*" $i || echo "info.app.version=$1" >> $i
    done
}

newVersion=$1
nextVersion=$2

echo Setting new version $newVersion

git checkout master
git pull
git merge origin/dev
./mvnw versions:set -DnewVersion=$newVersion
sed -i.bak "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$newVersion<\/dr-spring.version>|" pom.xml
setAppVer $newVersion
git add pom.xml */pom.xml */application.properties
git commit -m "set release version $newVersion :octopus:"
git push
git tag v$newVersion
git push origin v$newVersion
git checkout -b $newVersion.x
git push --set-upstream origin $newVersion.x

echo Setting next version $nextVersion

git checkout master
./mvnw versions:set -DnewVersion=$nextVersion
sed -i.bak "s|<dr-spring.version>.*<\/dr-spring.version>|<dr-spring.version>$nextVersion<\/dr-spring.version>|" pom.xml
setAppVer $nextVersion
git add pom.xml */pom.xml */application.properties
git commit -m "set snapshot version $nextVersion :snake:"
git push
git checkout dev
git pull
git merge master
git push

echo Cleaning Up

rm -rf pom.xml.*

