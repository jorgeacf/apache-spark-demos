package com.jorgefigueiredo.operators

import com.jorgefigueiredo.SparkContextFactory
import org.apache.spark.scheduler._
import org.apache.spark.{SparkConf, SparkContext}

object SparkListenerApplication {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf()
    sparkConf.setAppName("TestApplicationName")
    sparkConf.set("spark.extraListeners", "com.jorgefigueiredo.operators.CustomSparkListener")
    SparkContextFactory.setMaster(sparkConf)

    val sparkContext = new SparkContext(sparkConf)

    val items = sparkContext.parallelize(1 to 100, 2)
    val result = items.count()

    println(result)
  }
}

class CustomSparkListener extends SparkListener {

  override def onApplicationStart(applicationStart: SparkListenerApplicationStart): Unit = {
    println("MESSAGE onApplicationStart")
  }
  override def onApplicationEnd(applicationEnd: SparkListenerApplicationEnd): Unit = {
    println("MESSAGE onApplicationEnd")
  }
  override def onExecutorAdded(executorAdded: SparkListenerExecutorAdded): Unit = {
    println("MESSAGE onExecutorAdded: " + executorAdded.executorId)
  }
  override def onTaskStart(taskStart: SparkListenerTaskStart): Unit = {
    println("MESSAGE onTaskStart: " + taskStart.taskInfo.executorId)
  }

}
