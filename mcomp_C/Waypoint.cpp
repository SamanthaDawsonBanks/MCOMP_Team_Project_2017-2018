/*
 * Waypoint.cpp
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery
 */

#include "Waypoint.h"

Waypoint::Waypoint(int xIn, int yIn) {
  // TODO Auto-generated constructor stub
  x = xIn;
  y = yIn;
}

Waypoint::~Waypoint() {
  // TODO Auto-generated destructor stub
}

int Waypoint::getX() {
  return x;
}

int Waypoint::getY() {
  return y;
}
