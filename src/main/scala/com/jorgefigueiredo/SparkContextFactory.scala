package com.jorgefigueiredo

import org.apache.spark.{SparkConf, SparkContext}

object SparkContextFactory {

  def getLocalContext() : SparkContext = {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("TestApplicationName")
    sparkConf.setMaster("local[4]")
    sparkConf.set("spark.ui.showConsoleProgress", "false")

    val sparkContext = new SparkContext(sparkConf)
    sparkContext
  }

}
