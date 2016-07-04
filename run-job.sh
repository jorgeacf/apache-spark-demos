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

hash=`find infrastructure -type f -exec md5sum {} \; | md5sum`
echo ${hash}

mvn clean package -DskipTests
exitScriptIfFail

docker cp target/spark-1.0-SNAPSHOT-jar-with-dependencies.jar spark:/

docker cp input/. hadoop-master:/input

docker exec hadoop-master /bin/bash -c "export HADOOP_CONF_DIR=/home/jorgeacf/Code/Github/dockerfiles/hadoop/config"

docker exec hadoop-master hdfs dfs -put input /user/root

docker exec spark \
    spark-submit \
    --conf spark.driver.allowMultipleContexts=true \
    --class $1 \
    --master yarn-cluster \
    --driver-memory 1g \
    --num-executors 4 \
    --executor-memory 1g \
    --executor-cores 1 \
    spark-1.0-SNAPSHOT-jar-with-dependencies.jar

#docker exec spark rm -r -f result

#docker exec spark /hadoop/bin/hdfs dfs -get /user/root/result

#docker exec spark /hadoop/bin/hdfs dfs -rm -r /user/root/result

#sudo rm -r -f output

#sudo mkdir output

#sudo docker cp spark:/result/. output

#sudo chown -R jorgeacf output
#sudo chown -R jorgeacf:jorgeacf output
