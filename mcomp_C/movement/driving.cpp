/*
 * driving.cpp
 *
 *  Created on: 11 Mar 2018
 *      Author: Stephen Pope 15836791
 */

#include "driving.h"

driving::driving() {
	//TODO add the Maths parts

}

driving::~driving() {
	//releaseMotors() ??;
	delete leftMotor;
	delete rightMotor;
	delete AFMS;
}

void releaseMotors(){
	//The purpose of this will be for safety when STOPPING the bot.
	//When the robot finishes pulsing the motors, they remain locked.
	//This will be used to allow us to transport the robot.
	leftMotor.release();
	rightMotor.release();
}

Waypoint driveTo(Waypoint w){
	int numForwardSteps;//worked out using maths;
	bool ultraSoundTest = true;
	//TODO Maths needs to be merged in.

	/*
	 * The robot must turn first, then move in a straight line. This will be handled
	 * by two seperate loops. The maths will have worked out how many turn pulses need to happen.
	 */
	int turnPulses;//Again from Harrys code
	double theta; //TODO temp value delete me when merged with Maths.
	for(int i = 0; i > 0; i--){
		if (theta> 0){ //Circle theory left is negative?
			leftMotor.onestep(FORWARD, DOUBLE); //Turns the bot right
			rightMotor.onestep(BACKWARD, DOUBLE);
		}
		else{
			leftMotor.onestep(BACKWARD, DOUBLE); //Turns the bot left
			rightMotor.onestep(FORWARD, DOUBLE);
		}
	}

	//TODO Maybe validate that there has been a correct turn using the compass?

	/*
	 * This loop controls the forward movement. As the robot is now moving in an area bigger than
	 * its own dimensions, we need to check that there are not any obstacles.
	 *
	 * The maths for this needs explaining. As we have already turned, the Waypoint returned in
	 * the event of an interruption by the Ultra Sound sensor is
	 * the target x and y values multiplied by the % of steps we have taken on the way.
	 * This is because as we have turned, we are effectively looking at the ratio as if it is a
	 * straight line.
	 */
	for(int i = numForwardSteps; i > 0; i--){
		if(ultraSoundTest == true){ //TODO Obviously this needs to change!
			return new Waypoint((w.getX()*(i/numForwardSteps)),(w.getY()*(i/numForwardSteps)));
		}
		leftMotor.onestep(FORWARD, DOUBLE); //motor.step(number of steps, FORWARD or BACKWARD, SINGLE DOUBLE or INTERLEAVED)
		rightMotor.onestep(FORWARD, DOUBLE);//motor.onestep(Direction, Type);
	}
	return w;
}

