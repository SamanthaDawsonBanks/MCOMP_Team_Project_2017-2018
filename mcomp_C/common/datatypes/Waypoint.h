/*
 * Waypoint.h
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery
 */


#ifndef WAYPOINT_H_
#define WAYPOINT_H_

#include "AngleDistance.h"

class Waypoint {
 private:
  int x;
  int y;

 public:
  Waypoint();
  Waypoint(int xIn, int yIn);
  Waypoint(AngleDistance);
  virtual ~Waypoint();
  int getX();
  int getY();
  AngleDistance toAngleDistance();

};

#endif /* WAYPOINT_H_ */
