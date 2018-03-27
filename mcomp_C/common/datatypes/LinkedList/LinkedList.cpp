/*
 * LiDARRead.cpp
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "LinkedList.h"

LinkedList::LinkedList() {
  head = nullptr;
  lastItem = nullptr;
  length = 0;
}

LinkedList::~LinkedList() {
  // TODO Auto-generated destructor stub
}

void LinkedList::addNode(Waypoint w) {

  if (length > 0) {
    (*lastItem).setNext(new LinkedItem(w));
    lastItem = (*lastItem).getNext();
  } else {
    head = new LinkedItem(w);
    lastItem = head;
  }

  length++;

}

Waypoint LinkedList::poll() {
  Waypoint res = (*head).getData();
  if (length > 0) {
    res = (*head).getData();
    head = (*head).getNext();

    if (head == nullptr) {
      lastItem = nullptr;
    }
    length--;
  }
  return res;
}

int LinkedList::getLength() {
  return length;
}
