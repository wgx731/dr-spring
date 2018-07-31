#!/usr/bin/env bash

echo "[CLEAN-UP] source config.sh ..."
source $PWD/scripts/config.sh || exit 10

echo "[CLEAN-UP] cleaning up docker image ..."
docker rmi $(docker images --filter "dangling=true" -q --no-trunc)
docker rmi -f wgx731/dr-spring:${RELEASE_VERSION}
docker rmi -f wgx731/dr-spring:${SNAPSHOT_VERSION}

echo "[CLEAN-UP] cleaning up docker container ..."
docker rm $(docker ps -qa --no-trunc --filter "status=exited")

echo "[CLEAN-UP] cleaning up docker volume ..."
docker volume rm $(docker volume ls -qf dangling=true)
docker volume prune -f

echo "[CLEAN-UP] cleaning up docker network ..."
docker network rm $(docker network ls | grep "bridge" | awk '/ / { print $1 }')
docker network prune -f

echo "[CLEAN-UP] cleaning up target folder ..."
$PWD/mvnw clean || exit 11
