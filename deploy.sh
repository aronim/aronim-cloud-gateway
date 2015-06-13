#!/usr/bin/env bash

curl -i -XDELETE \
    "http://dev.kungfudev.io:8080/v2/apps/kdc-gateway"

sleep 2

curl -i -XPOST \
    -H "Content-Type: application/json" \
    -d @marathon.json \
    "http://dev.kungfudev.io:8080/v2/apps"