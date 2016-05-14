#!/bin/bash

docker stop $(docker ps -a -q -f name=jfdocker)
docker rm $(docker ps -a -q -f name=jfdocker)
