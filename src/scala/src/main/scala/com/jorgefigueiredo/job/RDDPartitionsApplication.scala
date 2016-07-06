package com.jorgefigueiredo.job

import java.io.File

import com.jorgefigueiredo.SparkContextFactory
import com.jorgefigueiredo.data.ItemListFactory
import org.apache.hadoop.fs.FileUtil

object RDDPartitionsApplication {

  def main(args: Array[String]) {
    val sparkContext = SparkContextFactory.getLocalContext()
    val numberOfItems = 100
    val numberOfPartitions = 10
    val input = ItemListFactory.get(numberOfItems)
    val partitions = sparkContext.parallelize(input, numberOfPartitions)

    val items = partitions
      .map(item => Array(item.id, item.name).mkString(", "))

    items.foreachPartition(p => p.foreach(println))

    /*
      val filePath = "output"
      FileUtil.fullyDelete(new File(filePath))
      items.saveAsTextFile(filePath)
      println(partitions.count())
    */
  }
}
