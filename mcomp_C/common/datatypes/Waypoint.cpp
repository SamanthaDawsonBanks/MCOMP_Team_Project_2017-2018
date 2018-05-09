/*
 * Waypoint.cpp
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *      Author: Stephen Pope 15836791
 *
 */

#include "Waypoint.h"
#include <math.h>

Waypoint::Waypoint() {
  // TODO Auto-generated constructor stub
  x = 0;
  y = 0;
}

Waypoint::Waypoint(double xIn, double yIn) {
  // TODO Auto-generated constructor stub
  x = xIn;
  y = yIn;
}

Waypoint::Waypoint(AngleDistance a) {
  // TODO Auto-generated constructor stub
  a.getDistance();
  y = a.getDistance() * sin(a.getTheta()* (M_PI / 180));
  x = a.getDistance() * cos(a.getTheta()* (M_PI / 180));
}

Waypoint::~Waypoint() {
  // TODO Auto-generated destructor stub
}

double Waypoint::getX() {
  return x;
}

double Waypoint::getY() {
  return y;
}

AngleDistance Waypoint::toAngleDistance() {
  return AngleDistance(atan2(y, x) * (180 / M_PI), hypot(x, y));  //TODO CHECK ATAN2 RETURN VALUE
}
