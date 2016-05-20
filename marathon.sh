#!/usr/bin/env bash

curl -i -XDELETE \
    "http://dev.aronim.com:8080/v2/apps/ac-gateway"

cp src/main/docker/Dockerfile build/libs/

docker rmi -f hub.aronim.com/kungfudev-cloud-gateway

docker build -t hub.aronim.com/kungfudev-cloud-gateway ./build/libs

docker push hub.aronim.com/kungfudev-cloud-gateway

curl -i -XPOST \
    -H "Content-Type: application/json" \
    -d @marathon.json \
    "http://dev.aronim.com:8080/v2/apps"