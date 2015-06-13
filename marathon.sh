#!/usr/bin/env bash

curl -i -XDELETE \
    "http://dev.kungfudev.io:8080/v2/apps/kdc-gateway"

cp src/main/docker/Dockerfile build/libs/

docker rmi -f hub.kungfudev.io/kungfudev-cloud-gateway

docker build -t hub.kungfudev.io/kungfudev-cloud-gateway ./build/libs

docker push hub.kungfudev.io/kungfudev-cloud-gateway

curl -i -XPOST \
    -H "Content-Type: application/json" \
    -d @marathon.json \
    "http://dev.kungfudev.io:8080/v2/apps"