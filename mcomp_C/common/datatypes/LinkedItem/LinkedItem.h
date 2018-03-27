/*
 * LinkedItem.h
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */

#ifndef LinkedItem_H_
#define LinkedItem_H_

#include "../Waypoint.h"

class LinkedItem {

 private:
  LinkedItem* next;
  Waypoint data;

 public:
  LinkedItem(Waypoint w);
  ~LinkedItem();

  Waypoint getData();
  LinkedItem* getNext();
  bool setNext(LinkedItem* next);
};

#endif /* LinkedItem_H_ */
