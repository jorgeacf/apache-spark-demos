package com.jorgefigueiredo.parallelize

import org.apache.spark.scheduler.SparkListener
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ParallelizeTest extends FunSuite {

  val sparkConf = new SparkConf()
  sparkConf.setAppName("Spark Job")
  sparkConf.setMaster("local[4]")
  sparkConf.set("spark.driver.allowMultipleContexts", "true")
  val sparkContext = new SparkContext(sparkConf)

  test("Parallelize with 5 partitions") {

    val numberOfPartitions = 5
    val range = 1 to 10000
    val items = sparkContext.parallelize(range, numberOfPartitions)
    val filterItems = items.filter(item => item % 2 ==0)
    val result = filterItems.count()

    assertEquals(numberOfPartitions, items.partitions.length)
    assertEquals(range.end / 2, result)
  }

  test("Parallelize foreach partition") {

    sparkContext.addSparkListener(new SparkListener {

    })

    val numberOfPartitions = 5
    val range = 1 to 10000
    val items = sparkContext.parallelize(range, numberOfPartitions)

    assertEquals(5, items.partitions.length)
  }

}
