#!/usr/bin/env bash

mvn clean package -DskipTests

docker cp target/spark-1.0-SNAPSHOT-jar-with-dependencies.jar jfdocker-YARN:/

docker cp input/. jfdocker-YARN:/input

docker exec jfdocker-YARN hdfs dfs -put input/text.txt /user/root

docker exec jfdocker-YARN \
    spark-submit \
    --conf spark.driver.allowMultipleContexts=true \
    --class com.jorgefigueiredo.WordCountApplication \
    --master yarn-cluster \
    --driver-memory 1g \
    --num-executors 4 \
    --executor-memory 1g \
    --executor-cores 1 \
    spark-1.0-SNAPSHOT-jar-with-dependencies.jar

docker exec jfdocker-YARN rm -r -f result

docker exec jfdocker-YARN hdfs dfs -get /user/root/result

docker exec jfdocker-YARN hdfs dfs -rm -r /user/root/result

sudo rm -r -f output

sudo mkdir output

sudo docker cp jfdocker-YARN:/result/. output

sudo chown -R jorgeacf output
sudo chown -R jorgeacf:jorgeacf output