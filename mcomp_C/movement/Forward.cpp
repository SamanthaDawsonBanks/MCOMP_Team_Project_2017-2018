/*
 * Forward.cpp
 *
 *  Created on: 22 Mar 2018
 *      Author: Stephen Pope 15836791
 *
 * Forward Method
 *
 * The Drive method hands this method a distance that must be t
 */

#include "Forward.h"

long forward (long distance){
	bool ultraSoundTest = false;
	double pulseTravel = wheelSize / stepsInRev;
	int numForwardSteps = (int) distance / pulseTravel;
//TODO Factor i out of this! See Uber
	for(int i = numForwardSteps; i > 0; i--){
			if(ultraSoundTest == true){ //TODO Interrupt needs adding
				return numForwardSteps - i * pulseTravel;
			}
			leftMotor.onestep(FORWARD, DOUBLE); //motor.step(number of steps, FORWARD or BACKWARD, SINGLE DOUBLE or INTERLEAVED)
			rightMotor.onestep(FORWARD, DOUBLE);//motor.onestep(Direction, Type);
		}
	return distance;
}

