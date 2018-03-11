/*
 * driving.cpp
 *
 *  Created on: 11 Mar 2018
 *      Author: Stephen Pope 15836791
 */

#include "driving.h"

Adafruit_MotorShield AFMS;
Adafruit_StepperMotor leftMotor;
Adafruit_StepperMotor rightMotor;

driving::driving() {
	//TODO add the Maths parts
	AFMS = Adafruit_MotorShield(); //The MotorShield
	leftMotor = AFMS.getStepper(200,1); //360(div)1.8 degree steps, M1 and M2 on the MotorShield
	rightMotor = AFMS.getStepper(200, 2); //M3 and M4 on the MotorShield
	leftMotor.setSpeed(30); //Set speed of rotation in RPM, 100Hz * 1.8 Degrees = 1 rotation of wheel every 2 seconds.
	//TODO change to 400?
	rightMotor.setSpeed(30);
}

driving::~driving() {
	// TODO Auto-generated destructor stub
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
	int numSteps;//worked out using maths;
	leftMotor.step(1, FORWARD, DOUBLE); //motor.step(number of steps, FORWARD or BACKWARD, SINGLE DOUBLE or INTERLEAVED)
	rightMotor.step(1, BACKWARD, DOUBLE);
	return nullptr;
}

