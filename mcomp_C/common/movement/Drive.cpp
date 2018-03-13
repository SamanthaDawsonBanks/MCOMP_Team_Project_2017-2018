/*
 * Drive.cpp
 *
 *  Created on: 13 Mar 2018
 *      Author: hj100
 */

#include "Drive.h"

#include <math.h>
#include "../datatypes/Waypoint.h"
#include "Drive.h"

#define PI 3.14159265

Drive::Drive(double x, double y){

  double toDeg = PI/180.0;

  Waypoint robot(0, 0);
  Waypoint target(-10, 5);

  double targetX = (double)target.getX();
  double targetY = (double)target.getY();

  double robotX = (double)robot.getX();
  double robotY = (double)robot.getY();

  dx = targetX - robotX;
  dy = targetY - robotY;
  distance = sqrt((dx*dx) + (dy*dy));


  theta = atan2(dy,dx)*toDeg;
  phi = 0.0;
  R = phi - theta;


  wheelRadius = 0.0;
  robotRadius = 0.0;
  totalTurnAngle = 0.0;


  pulse = (2 * PI*wheelRadius)*(1.8 / 360);
  turnDistance = totalTurnAngle / pulse;
  moveDistance = distance / pulse;

}

Drive::~Drive() {
  // TODO Auto-generated destructor stub
}





