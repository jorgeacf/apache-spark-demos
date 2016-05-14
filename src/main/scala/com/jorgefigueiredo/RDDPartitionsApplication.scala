package com.jorgefigueiredo

import org.apache.spark.{SparkConf, SparkContext}


object RDDPartitionsApplication {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("RDDPartitionsApplication")

    val sparkContext = new SparkContext(sparkConf)
    val partitions = sparkContext.parallelize(1 to 50, 5)

    partitions.saveAsTextFile("file.csv")

    println(partitions.count())
  }
}
