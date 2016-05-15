package com.jorgefigueiredo

import org.apache.spark.{SparkConf, SparkContext}

object SparkContextFactory {

  def getLocalContext() : SparkContext = {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("TestApplicationName")
    sparkConf.setMaster("local[4]")

    val sparkContext = new SparkContext(sparkConf)
    sparkContext
  }

}
