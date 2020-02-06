#!/bin/bash

docker stop servicefromdata
# docker rm servicefromdata

docker build -t servicefromdata .
docker run -it --rm -p 80:8080 --net d2s-cwl-workflows_network -e ENDPOINT="http://graphdb:7200" servicefromdata