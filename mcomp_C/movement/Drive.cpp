/*
 * Drive.cpp
 *
 *  Created on: 22 Mar 2018
 *      Author: Stephen Pope 15836791
 *
 *
 * Drive method
 *
 * This method allows a robot to drive from its current position (assumed to be 0,0) to
 * the location handed to it. The position relative to the world is not handled by this
 * method, that is handled in the partner Java application.
 *
 * In order to drive to the next point along a Path, the robot needs to assess the angle
 * it needs to turn by to point at the Waypoint provided, then how far it needs to travel
 * to reach it. As a Path is split into small sections by the Java code, we can assume
 * that only one rotation and forward movement are required.
 *
 * If the journey is successful and the robot reaches its destination, it returns the
 * Waypoint it was handed. If the robot becomes blocked along its path, then the robot
 * returns a Waypoint with a value representing how far it was able to travel, effectively
 * its new location relative to where it started the current drive call.
 */

#include "Drive.h"

Waypoint Drive (Waypoint w){
	AngleDistance movement((atan2(w.getY(),w.getX())) * (180 / M_PI) , hypot(w.getX(),w.getY())); //TODO CHECK ATAN2 RETURN VALUE
	double rotate(movement.getTheta());
	if (rotate == movement.getTheta()){
		long forward(movement.getDistance());
		if (forward == movement.getDistance()){
			return w;
		}
		else return Waypoint(forward/w.getX(), forward/w.getY());
	}
	else return Waypoint (0,0);
}

