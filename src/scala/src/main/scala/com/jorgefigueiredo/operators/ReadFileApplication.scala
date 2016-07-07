package com.jorgefigueiredo.operators

import org.apache.spark.{SparkConf, SparkContext}

object ReadFileApplication {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("Spark Read File Application")
    //sparkConf.setMaster("local[2]")

    val sparkContext = new SparkContext(sparkConf)

    val data = sparkContext.textFile("input/text.txt", 2).cache()

    val apacheCount = data.filter(line => line.contains("dataset")).count()

    println(apacheCount)
  }

}
