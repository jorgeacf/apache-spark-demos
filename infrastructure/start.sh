#!/bin/bash

echo 'Creating YARN container...'
$(dirname -- $(readlink -fn -- "$0"))/YARN/start.sh

echo 'Creating HBase container...'
docker-compose -f $(dirname -- $(readlink -fn -- "$0"))/HBase/docker-compose.yml up -d


