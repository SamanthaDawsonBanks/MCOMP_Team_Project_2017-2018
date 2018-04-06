/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */

#ifndef PATH_H_
#define PATH_H_

#include "../Waypoint.h"
#include "../LinkedItem/LinkedItem.h"

class Path {

 private:
  LinkedItem* head;
  LinkedItem* destination;
  LinkedItem* lastItem;
  int length;

 public:
  Path(Waypoint w);
  virtual ~Path();
  void addNode(Waypoint w);
  Waypoint poll();
  int getLength();

};
//FIXME implement this once data structure is defined in java land

#endif /* PATH_H_ */
