/*
 * Propulsion.cpp
 *
 *  Created on: 26 Mar 2018
 *      Author: Stephen Pope 15836791
 *      Author: David Avery 15823926
 */

#include "Propulsion.h"
#include "..\common\datatypes\AngleDistance.h"
#include "..\config\robot_config.h"

Propulsion::Propulsion() {
  //MotorShield Setup
  AFMS = Adafruit_MotorShield(); //The MotorShield
  leftMotor = (*AFMS.getStepper(200,1)); //360(div)1.8 degree steps, M1 and M2 on the MotorShield
  rightMotor = (*AFMS.getStepper(200, 2)); //M3 and M4 on the MotorShield
  leftMotor.setSpeed(30); //Set speed of rotation in RPM, 100Hz * 1.8 Degrees = 1 rotation of wheel every 2 seconds, or 30 RPM.
  //TODO change to 400Hz, or 120 rpm?
  rightMotor.setSpeed(30);
  currentHeading = 0; //TODO Constructor may need to take a starting angle.
}

Propulsion::~Propulsion() {
  releaseMotors();
  delete leftMotor;
  delete rightMotor;
  delete AFMS;
  delete currentHeading;
}

Waypoint Propulsion::Drive (Waypoint w){
  /**
   * atan2 values, 0 = East (Positive X axis), M_PI/2 = North (Positive Y Axis)
   *         Negative M_PI/2 = South (Negative Y Axis)
   *         M_PI = West (Negative X Axis)
   * See http://en.cppreference.com/w/cpp/numeric/math/atan2 for more.
   * Values returned are in Radians, so value * 180/pi = degrees
   */
  AngleDistance movement = w.toAngleDistance();
  currentHeading += rotate(movement.getTheta()-currentHeading);
  long distance = forward(movement.getDistance());
  return Waypoint((distance/movement.getDistance()) * w.getX(), (distance/movement.getDistance()) * w.getY());
}


double Propulsion::rotate (double theta){

  double bodyRotation = ((2 * M_PI) * (wheelTrack/2)) * (theta/360);
  double pulseDistance = ((2 * M_PI) * wheelSize) * (stepsInRev/360);
  int turnPulses = (int) abs(bodyRotation / pulseDistance);

  for(int i = turnPulses; i > 0; i--){
    if (theta < 0){
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


long Propulsion::forward (long distance){
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


void Propulsion::releaseMotors(){
  //The purpose of this will be for safety when STOPPING the bot.
  //When the robot finishes pulsing the motors, they remain locked.
  //This will be used to allow us to transport the robot.
  leftMotor.release();
  rightMotor.release();

}

