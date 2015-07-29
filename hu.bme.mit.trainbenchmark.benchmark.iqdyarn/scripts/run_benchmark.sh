#!/bin/bash

autossh -M 0 -q -f -N -o "ServerAliveInterval 10" -o "ServerAliveCountMax 3" -L 2181:localhost:2181 root@yarn-rm.docker

java -jar hu.bme.mit.trainbenchmark.benchmark.iqdyarn-1.0.0-SNAPSHOT.jar \
        -size $1 \
        -query RouteSensor \
        -scenario Repair \
        -modificationMethod resultSet \
        -modificationConstant 10

