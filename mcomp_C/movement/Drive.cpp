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
	/**
	 * atan2 values, 0 = East (Positive X axis), M_PI/2 = North (Positive Y Axis)
	 * 				 Negative M_PI/2 = South (Negative Y Axis)
	 * 				 M_PI = West (Negative X Axis)
	 * See http://en.cppreference.com/w/cpp/numeric/math/atan2 for more.
	 * Values returned are in Radians, so value * 180/pi = degrees
	 */
	AngleDistance movement((atan2(w.getY(),w.getX())) * (180 / M_PI) , hypot(w.getX(),w.getY()));
	double rotate(movement.getTheta());
	long forward(movement.getDistance());
    return Waypoint((forward/movement.getDistance()) * w.getX(), (forward/movement.getDistance()) * w.getY());
	}

