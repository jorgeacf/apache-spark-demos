#!/usr/bin/env bash

mvn clean package -DskipTests

docker cp target/spark-1.0-SNAPSHOT-jar-with-dependencies.jar chaos-YARN:/

docker exec chaos-YARN \
    spark-submit \
    --conf spark.driver.allowMultipleContexts=true \
    --class com.jorgefigueiredo.SparkListenerApplication \
    --master yarn-cluster \
    --driver-memory 1g \
    --num-executors 4 \
    --executor-memory 1g \
    --executor-cores 1 \
    spark-1.0-SNAPSHOT-jar-with-dependencies.jar
