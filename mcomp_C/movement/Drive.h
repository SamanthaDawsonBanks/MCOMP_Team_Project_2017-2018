/*
 * Drive.h
 *
 *  Created on: 22 Mar 2018
 *      Author: Stephen Pope 15836791
 */

#ifndef COMMON_MOVEMENT_DRIVE_H_
#define COMMON_MOVEMENT_DRIVE_H_

#include "..\common\datatypes\Waypoint.h";
#include "..\maths\Forward.h";
#include <math.h>
#include "../maths/Rotate.h"

class Drive {
public:
	Waypoint drive (Waypoint w);
};

#endif /* COMMON_MOVEMENT_DRIVE_H_ */
