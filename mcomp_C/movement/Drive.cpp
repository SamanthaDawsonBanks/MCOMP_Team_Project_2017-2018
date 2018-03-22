/*
 * Drive.cpp
 *
 *  Created on: 22 Mar 2018
 *      Author: Stephen Pope 15836791
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

