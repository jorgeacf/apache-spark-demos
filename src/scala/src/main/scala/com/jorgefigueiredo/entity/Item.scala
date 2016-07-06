package com.jorgefigueiredo.entity

case class Item(id: Int, name: String, itemType: String) {
  override def toString() = s"[  Item id=[$id], name=[$name], itemType=[$itemType]  ]"
}
