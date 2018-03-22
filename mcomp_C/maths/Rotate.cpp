/*
 * Turn.cpp
 *
 *  Created on: 22 Mar 2018
 *      Author: Stephen Pope 15836791
 */

#include "Rotate.h"

double rotate (double theta){

	double bodyRotation = ((2 * M_PI) * (wheelTrack/2)) * (theta/360);
	double pulseDistance = ((2 * M_PI) * wheelSize) * (stepsInRev/360);
}

}

Rotate::~Rotate() {
	// TODO Auto-generated destructor stub
}

