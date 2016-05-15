package com.jorgefigueiredo

import org.apache.spark.{SparkConf, SparkContext}

object WordCountApplication {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("WordCountApplication")
    val sparkContext = new SparkContext(sparkConf)

    val textFile = sparkContext.textFile("text.txt")
    val counts = textFile
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .sortByKey(true)
      //.filter(item => item._1.contains("a"))

    counts.saveAsTextFile("result")

  }

}
