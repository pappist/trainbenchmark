#!/bin/bash

source profile.sh

docker stop $TB_NAME
docker rm $TB_NAME
