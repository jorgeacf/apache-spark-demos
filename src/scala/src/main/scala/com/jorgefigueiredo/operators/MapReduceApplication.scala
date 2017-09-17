package com.jorgefigueiredo.operators

import com.jorgefigueiredo.SparkContextFactory
import com.jorgefigueiredo.data.ItemListFactory

object MapReduceApplication {

  def main(args: Array[String]) {
    val sparkContext = SparkContextFactory.getContext
    val numberOfItems = 10
    val numberOfPartitions = 2
    val input = ItemListFactory.get(numberOfItems, numberOfPartitions)
    println(input)
    val items = sparkContext.parallelize(input, numberOfPartitions)
    //val sorted = items.sortBy(a => a.id, true)
    //sorted.foreach(item => println(item))
    val map = items.map(item => (item.itemType, item.id))
    val reduce = map.reduceByKey((accum, n) => accum + n)
    reduce.foreach(a=> println(a))
  }
}
