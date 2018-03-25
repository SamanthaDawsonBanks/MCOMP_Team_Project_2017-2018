/*
 * LiDARRead.cpp
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "LiDARRead.h"

LiDARRead::LiDARRead() {
  head = nullptr;
  lastItem = nullptr;
  length = 0;
}

LiDARRead::~LiDARRead() {
  // TODO Auto-generated destructor stub
}

void LiDARRead::addNode(Waypoint w) {

  if (length > 0) {
    (*lastItem).setNext(new LinkedItem(w));
    lastItem = (*lastItem).getNext();
  } else {
    head = new LinkedItem(w);
    lastItem = head;
  }

  length++;

}

Waypoint LiDARRead::poll() {
  Waypoint res = nullptr;
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

int LiDARRead::getLength() {
  return length;
}
