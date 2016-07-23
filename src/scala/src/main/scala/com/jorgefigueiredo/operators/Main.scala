package com.jorgefigueiredo.operators

import org.apache.spark.{SparkConf, SparkContext}
import sys.process._

object Main {

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("Spark Job")
    //sparkConf.setMaster("local[*]")

    val sparkContext = new SparkContext(sparkConf)
    val list = sparkContext.parallelize(1 to 1000, 1)
    val filter = list.filter(item => item % 2 == 0)
    val count = filter.count

    println("RESULT: " + count)
  }

}