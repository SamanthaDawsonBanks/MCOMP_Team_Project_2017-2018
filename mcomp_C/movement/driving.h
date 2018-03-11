/*
 * driving.h
 *
 *  Created on: 11 Mar 2018
 *      Author: Steve
 */

#ifndef MOVEMENT_DRIVING_H_
#define MOVEMENT_DRIVING_H_
#include "Waypoint.h"

class driving {
public:
	driving();
	virtual ~driving();
	Waypoint driveTo(Waypoint);
};

#endif /* MOVEMENT_DRIVING_H_ */
