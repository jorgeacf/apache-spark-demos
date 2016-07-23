package com.jorgefigueiredo

import org.apache.spark.SparkConf
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.junit.Assert._

@RunWith(classOf[JUnitRunner])
class SparkContextFactoryTest extends FunSuite  {

  test("SparkContextFactory can return a SparkContext") {

    val sparkContext = SparkContextFactory.getContext()
    assertNotNull(sparkContext)
    sparkContext.stop()
  }

  test("SparkContextFactory can set the master") {
    val sparkConf = new SparkConf()
    SparkContextFactory.setMaster(sparkConf)
    assertNotNull(sparkConf)
  }

}
