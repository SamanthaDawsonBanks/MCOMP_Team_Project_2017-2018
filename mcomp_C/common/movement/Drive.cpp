/*
 * Drive.cpp
 *
  *  Created on: 22 February 2018
 *      Authors: Stephen Pope 15836791
 *      		 Harry Jackson 14812630
 *      		 David Avery 15823926
 */

#include <math.h>
#include "Waypoint.h"
#include "Drive.h"
#include <iostream>

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


