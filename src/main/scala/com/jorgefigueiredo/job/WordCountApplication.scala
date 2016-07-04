package com.jorgefigueiredo.job

import com.jorgefigueiredo.SparkContextFactory

object WordCountApplication {

  def main(args: Array[String]) {

    val sparkContext = SparkContextFactory.getLocalContext()

    val textFile = sparkContext.textFile("input/text.txt")
    val counts = textFile
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .sortByKey(true)
      //.filter(item => item._1.contains("a"))

    counts.saveAsTextFile("output")

    counts.foreach(println)

  }

}
