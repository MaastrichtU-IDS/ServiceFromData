#!/bin/bash

mvn package spring-boot:repackage
java -jar target/d2s-api-0.9.0.jar