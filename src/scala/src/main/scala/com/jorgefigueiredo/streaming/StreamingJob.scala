package com.jorgefigueiredo.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingJob {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("Spark Streaming Example")
    //sparkConf.setMaster("local[2]")

    val streamingContext = new StreamingContext(sparkConf, Seconds(1))

    val lines = streamingContext.socketTextStream("172.17.0.1", 9999)

    val words = lines.flatMap(_.split(" "))

    val pairs = words.map(word => (word, 1))

    val wordCounts = pairs.reduceByKey(_+_)

    // wordCounts.print()

    wordCounts.foreachRDD(rdd => {
      if(!rdd.isEmpty()) {
        rdd.saveAsTextFile("result")
      }
    })
    //wordCounts.saveAsTextFiles("result", "txt")

    println("Starting streaming operators...")
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
