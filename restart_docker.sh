#!/bin/bash

docker stop d2s-api
# docker rm d2s-api

docker build -t d2s-api .
docker run -it --rm -p 80:8080 --net d2s-core_network -e ENDPOINT="http://graphdb:7200" d2s-api