#!/usr/bin/env bash

# usage: ./ab-performance.sh grpc 1 1 10000

URL_BASE="http://127.0.0.1:8080/gateway/bermuda"

PROVIDER=$1 # provider: [grpc | dubbo]
NUM=$2 # number of data generated: (e.g. 10000) 
CONCURRENCY=$3 # concurreny level: (e.g. 10)
REQUEST=$4 # number of total request made: (e.g. 10000)

echo "[AB-PERFORMANCE] concurrency - $CONCURRENCY"
echo "[AB-PERFORMANCE] requests - $REQUEST"
echo "[AB-PERFORMANCE] testing generating $NUM data with $PROVIDER"
ab \
    -c ${CONCURRENCY} \
    -n ${REQUEST} \
    -g ${PWD}/performance/${PROVIDER}_${NUM}_${CONCURRENCY}_${REQUEST}.dat \
    "$URL_BASE/$PROVIDER/$NUM" > ${PWD}/performance/${PROVIDER}_${NUM}_${CONCURRENCY}_${REQUEST}.txt
