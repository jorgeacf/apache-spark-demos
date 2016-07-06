package com.jorgefigueiredo.data

import org.junit.runner.RunWith
import org.junit.Assert._
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ItemListFactoryTest extends FunSuite {

  test("ItemListFactory get method returns more than one item") {

    val numberOfItems = 10
    val numberOfPartitions = 2
    val items = ItemListFactory.get(numberOfItems, numberOfPartitions)
    val ids = items.map(item => item.id)

    assertNotNull(items)
    assertEquals(numberOfItems, items.length)
    assertEquals(Range(0,items.length).reverse, ids)
  }

}
