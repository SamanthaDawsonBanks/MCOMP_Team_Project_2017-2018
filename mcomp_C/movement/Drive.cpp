/*
 * Drive.cpp
 *
 *  Created on: 22 Mar 2018
 *      Author: Stephen Pope 15836791
 */

#include "Drive.h"

Waypoint Drive (Waypoint w){
	double theta = atan2(w.getY(),w.getX());
	turn(theta);
	forward(hypot(w.getX(),w.getY()));
	return nullptr;
}

