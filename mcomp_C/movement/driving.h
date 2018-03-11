/*
 * driving.h
 *
 *  Created on: 11 Mar 2018
 *      Author: Steve
 */

#ifndef MOVEMENT_DRIVING_H_
#define MOVEMENT_DRIVING_H_
#include "../common/datatypes/Waypoint.h"
#include <Wire.h>
#include "../libraries/Adafruit_MS_PWMServoDriver.h"
#include "../libraries/Adafruit_MotorShield.h"

class driving {
public:
	driving();
	virtual ~driving();
	Waypoint driveTo(Waypoint);
	void unlockMotors();
};

#endif /* MOVEMENT_DRIVING_H_ */
