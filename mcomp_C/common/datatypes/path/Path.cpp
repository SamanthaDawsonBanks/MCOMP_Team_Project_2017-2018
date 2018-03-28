/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */
#include "Path.h"
#include "../Waypoint.h"

int length;

Path::Path(Waypoint w) {
  destination = new LinkedItem(w);
  head = nullptr;
  lastItem = nullptr;
  length = 0;

}

Path::~Path() {
  // TODO Auto-generated destructor stub
}

void Path::addNode(Waypoint w) {

  if (length > 0) {
    (*lastItem).setNext(new LinkedItem(w));
    lastItem = (*lastItem).getNext();
  } else {
    head = new LinkedItem(w);
    lastItem = head;
  }

  length++;
}

Waypoint Path::poll() {

  Waypoint res = (*destination).getData();
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

int Path::getLength() {
  return length;
}
