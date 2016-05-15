package com.jorgefigueiredo.data

import com.jorgefigueiredo.entity.Item

object ItemListFactory {

  def get(numberOfItems: Int) : List[Item] = {
    get(numberOfItems, 1)
  }

  def get(numberOfItems: Int, numberOfPartitions: Int): List[Item] = {

    val partitionSize = numberOfItems / numberOfPartitions
    var itemCount = 0
    var partitionCount = 0
    var items = List[Item]()
    for(index <- Range(0, numberOfItems)) {
      if(itemCount >= partitionSize) {
        itemCount = 0
        partitionCount += 1
      }
      items = new Item(index, s"Item $index", ("a".charAt(0) + partitionCount).asInstanceOf[Char] + "") :: items
      itemCount += 1
    }
    items
  }

}
