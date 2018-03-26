/*
 * Rotate.cpp
 *
 *  Created on: 22 Mar 2018
 *      Author: Stephen Pope 15836791
 */

#include "..\movement\Rotate.h"

//TODO Comment this section as in old branch

double rotate (double theta){

	double bodyRotation = ((2 * M_PI) * (wheelTrack/2)) * (theta/360);
	double pulseDistance = ((2 * M_PI) * wheelSize) * (stepsInRev/360);
	int turnPulses = (int) bodyRotation / pulseDistance;

	for(int i = turnPulses; i > 0; i--){
		if(theta == 0){
			return theta;
		}
		else if (theta < 0){
			leftMotor.onestep(FORWARD, DOUBLE); //Turns the bot right
			rightMotor.onestep(BACKWARD, DOUBLE);
		}
		else{
			leftMotor.onestep(BACKWARD, DOUBLE); //Turns the bot left
			rightMotor.onestep(FORWARD, DOUBLE);
		}
	}
	return theta;
}
