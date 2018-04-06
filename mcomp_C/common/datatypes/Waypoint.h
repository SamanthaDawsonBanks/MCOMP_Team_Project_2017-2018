/*
 * Waypoint.h
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 */


#ifndef WAYPOINT_H_
#define WAYPOINT_H_

#include "AngleDistance.h"

class Waypoint {
 private:
  double x;
  double y;

 public:
  Waypoint();
  Waypoint(double xIn, double yIn);
  Waypoint(AngleDistance a);
  virtual ~Waypoint();
  double getX();
  double getY();
  AngleDistance toAngleDistance();

};

#endif /* WAYPOINT_H_ */
