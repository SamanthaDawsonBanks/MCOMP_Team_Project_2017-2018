/*
 * LinkedList.h
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#ifndef COMMON_DATATYPES_LINKEDLIST_LINKEDLIST_H_
#define COMMON_DATATYPES_LINKEDLIST_LINKEDLIST_H_

#include "../Waypoint.h"
#include "../LinkedItem/LinkedItem.h"

class LinkedList {
 private:
  LinkedItem* head;
  LinkedItem* lastItem;
  int length;

 public:
  LinkedList();
  virtual ~LinkedList();
  void addNode(Waypoint w);
  Waypoint poll();
  int getLength();

 public:
};

#endif /* COMMON_DATATYPES_LINKEDLIST_LINKEDLIST_H_ */
