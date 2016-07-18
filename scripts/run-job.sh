#!/usr/bin/env bash

echo "========================================"
echo "====== Spark Job Runner on Docker ======"
echo "========================================"
echo ""
echo ""
if [ -z "$1" ]
then
    echo "Missing SparkJob class name as first parameter"
    echo ""
    exit -1
fi

function exitScriptIfFail {
    status=$?
    if [ ${status} -ne 0 ];
    then
        echo "Exiting script with status [${status}]."
        exit ${status}
    fi
}

#touch /root/test 2> /dev/null

#hash=`find infrastructure -type f -exec md5sum {} \; | md5sum`
#echo ${hash}

cd src/scala

mvn clean package -DskipTests
exitScriptIfFail

docker cp target/spark-1.0-SNAPSHOT-jar-with-dependencies.jar spark:/
exitScriptIfFail

# REMOVE and add to the docker file
docker exec hadoop-master /bin/bash -c "export HADOOP_CONF_DIR=/hadoop/etc/hadoop/"
exitScriptIfFail

docker exec hadoop-master hdfs dfs -rm -r -f /user/root/input
exitScriptIfFail

docker exec hadoop-master hdfs dfs -rm -r -f /user/root/output
exitScriptIfFail

docker cp ../../input/. hadoop-master:/input
exitScriptIfFail

docker exec hadoop-master hdfs dfs -put input /user/root
exitScriptIfFail

docker exec spark \
    spark-submit \
    --conf spark.driver.allowMultipleContexts=true \
    --class $1 \
    --master yarn-client \
    --driver-memory 1g \
    --num-executors 4 \
    --executor-memory 1g \
    --executor-cores 1 \
    spark-1.0-SNAPSHOT-jar-with-dependencies.jar
exitScriptIfFail

docker exec hadoop-master rm -r -f output
exitScriptIfFail

docker exec hadoop-master hdfs dfs -get /user/root/output output
exitScriptIfFail

rm -r -f output/*
exitScriptIfFail

docker cp hadoop-master:/output/. output
exitScriptIfFail

chown -R jorgeacf output
chown -R jorgeacf:jorgeacf output
