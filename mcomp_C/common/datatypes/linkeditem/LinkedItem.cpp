/*
 * LinkedItem.cpp
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */
#include "../Waypoint.h"
#include "LinkedItem.h"

LinkedItem::LinkedItem(Waypoint w) {
  data = w;
  next = nullptr;
}

LinkedItem::~LinkedItem() {
  // TODO Auto-generated destructor stub
}

Waypoint LinkedItem::getData() {
  return data;
}

LinkedItem* LinkedItem::getNext() {
  return next;
}

bool LinkedItem::setNext(LinkedItem* nextItem) {
  next = nextItem;
  return true;
}
