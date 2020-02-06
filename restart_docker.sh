#!/bin/bash

docker stop servicefromdata
docker rm servicefromdata

docker build -t servicefromdata .
docker run -it --rm -p 80:8080 servicefromdata
