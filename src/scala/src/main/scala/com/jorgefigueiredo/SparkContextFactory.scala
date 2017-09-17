package com.jorgefigueiredo

import org.apache.spark.{SparkConf, SparkContext}

object SparkContextFactory {

  def getContext : SparkContext = {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("TestApplicationName")
    sparkConf.setMaster("local[4]")
    sparkConf.set("spark.ui.showConsoleProgress", "false")
    sparkConf.set("spark.driver.allowMultipleContexts", "true")

    val sparkContext = new SparkContext(sparkConf)
    sparkContext
  }

  def setMaster(sparkConf: SparkConf) : SparkConf = {
    sparkConf.setMaster("local[4]")
  }

}
