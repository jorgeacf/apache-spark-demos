package com.jorgefigueiredo.operators

import com.jorgefigueiredo.SparkContextFactory

object FileRDDApplication {

  def main(args: Array[String]) {

    val sparkContext = SparkContextFactory.getContext
    val countries = sparkContext.textFile("input/airports/countries.csv")
    //val airports = sparkContext.textFile("input/airports/airports.csv")

    //val countriesKeyValue = countries.map(country => (country.split(",")(1), country))
    //val airportsKeyValue = airports.map(airport => (airport.split(",")(8), airport))

    countries.take(10).foreach(println)
    //airports.take(10).foreach(println)

    //countriesKeyValue.take(10).foreach(println)
    //airportsKeyValue.take(10).foreach(println)

    //val airportsWithCountries = airportsKeyValue.join(countriesKeyValue)
    //airportsWithCountries.take(10).foreach(println)

    Thread.sleep(60*1000)

  }
}
