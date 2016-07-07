package com.jorgefigueiredo.operators

import com.jorgefigueiredo.SparkContextFactory
import org.apache.spark.scheduler._
import org.apache.spark.{SparkConf, SparkContext}

object SparkListenerApplication {

  def main(args: Array[String]) {

    val sparkContext = SparkContextFactory.getLocalContext()

    setSparkListener(sparkContext)
    val items = sparkContext.parallelize(1 to 100, 2)
    val result = items.count()

    println(result)
  }

  def setSparkListener(sparkContext: SparkContext) = {

    sparkContext.addSparkListener(new SparkListener()
    {
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
    })

  }

}
