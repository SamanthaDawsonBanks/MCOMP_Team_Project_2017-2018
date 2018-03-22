/*
 * driving.h
 *
 *  Created on: 11 Mar 2018
 *      Author: Stephen Pope 15836791
 */

#ifndef MOVEMENT_DRIVING_H_
#define MOVEMENT_DRIVING_H_
#include "../common/datatypes/Waypoint.h"
#include <Wire.h>
#include "../libraries/Adafruit_MotorShield.h"

class driving {
public:
	driving();
	~driving();
	Waypoint driveTo(Waypoint);
	void unlockMotors();

};


#endif /* MOVEMENT_DRIVING_H_ */
