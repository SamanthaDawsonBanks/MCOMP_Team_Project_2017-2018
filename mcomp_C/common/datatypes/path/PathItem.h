/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */
#ifndef PathItem_H_
#define PathItem_H_

#include "../Waypoint.h"
#include "PathItem.h"

class PathItem {

 private:
  PathItem* next;
  Waypoint data;

 public:
  PathItem(Waypoint);
  ~PathItem();

  Waypoint getData();
  PathItem* getNext();
  bool setNext(PathItem*);
};

#endif /* PathItem_H_ */
