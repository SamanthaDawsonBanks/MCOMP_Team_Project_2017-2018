/*
 * LiDARRead.h
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#ifndef COMMON_DATATYPES_LIDARREAD_LIDARREAD_H_
#define COMMON_DATATYPES_LIDARREAD_LIDARREAD_H_

#include "../Waypoint.h"
#include "../LinkedItem/LinkedItem.h"

class LiDARRead {
 private:
  LinkedItem* head;
  LinkedItem* lastItem;
  int length;

 public:
  LiDARRead(Waypoint w);
  virtual ~LiDARRead();
  void addNode(Waypoint w);
  Waypoint poll();
  int getLength();

 public:
};

#endif /* COMMON_DATATYPES_LIDARREAD_LIDARREAD_H_ */
