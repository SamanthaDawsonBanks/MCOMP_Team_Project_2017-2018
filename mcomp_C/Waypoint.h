/*
 * Waypoint.h
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery
 */

#ifndef WAYPOINT_H_
#define WAYPOINT_H_

class Waypoint {
 private:
  int x;
  int y;

 public:
  Waypoint::Waypoint(int xIn, int yIn);
  virtual ~Waypoint();
  int Waypoint::getX();
  int Waypoint::getY();

};

#endif /* WAYPOINT_H_ */
