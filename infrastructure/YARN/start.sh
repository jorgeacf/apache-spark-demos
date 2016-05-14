#!/bin/bash

echo 'Deleting jfdocker containers...'
$(dirname -- $(readlink -fn -- "$0"))/../stop.sh

echo 'Creating YARN container...'
docker run -d -p 8088:8088 -p 8042:8042 -p 4040:4040 -h sandbox --name jfdocker-YARN sequenceiq/spark:1.6.0 -d

echo 'Setting up ssh for the YARN container...'
docker run -d --name jfdocker-ssh -p 2222:22 -v /var/run/docker.sock:/var/run/docker.sock -e CONTAINER=jfdocker-YARN -e AUTH_MECHANISM=noAuth jeroenpeeters/docker-ssh
