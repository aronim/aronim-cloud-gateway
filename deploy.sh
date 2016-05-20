#!/usr/bin/env bash

curl -i -XDELETE \
    "http://dev.aronim.com:8080/v2/apps/ac-gateway"

sleep 2

curl -i -XPOST \
    -H "Content-Type: application/json" \
    -d @marathon.json \
    "http://dev.aronim.com:8080/v2/apps"