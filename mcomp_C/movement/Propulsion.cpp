/*
 * Propulsion.cpp
 *
 *  Created on: 26 Mar 2018
 *      Author: Stephen Pope 15836791

 *      Author: David Avery 15823926
 *
 * Propulsion class
 *
 * This class handles how a robot is able to move itself around the world.
 * Currently, only methods that involve driving are implemented but it would be
 * possible to extend this class to include methods for powering flight motors etc.
 */

#include "Propulsion.h"
#include "..\common\datatypes\AngleDistance.h"
#include "..\config\robot_config.h"

Propulsion::Propulsion() {
  //MotorShield Setup
  AFMS = Adafruit_MotorShield(); //The MotorShield
  AFMS.begin();
  leftMotor = (*AFMS.getStepper(200,1)); //360(div)1.8 degree steps, M1 and M2 on the MotorShield
  rightMotor = (*AFMS.getStepper(200, 2)); //M3 and M4 on the MotorShield
  leftMotor.setSpeed(30); //Set speed of rotation in RPM, 100Hz * 1.8 Degrees = 1 rotation of wheel every 2 seconds, or 30 RPM.
  //TODO change to 400Hz, or 120 rpm?
  rightMotor.setSpeed(30);
  currentHeading = 0; //TODO Constructor may need to take a starting angle.
}


Propulsion::~Propulsion() {
  releaseMotors();
}

/**
* Drive method
*
* This method allows a robot to drive from its current position (assumed to be 0,0) to
* the location handed to it. The position relative to the first call to drive is handled
* in this application, so that the robot can work out how much it must turn by.
*
* In order to drive to the next point along a Path, the robot needs to assess the angle
* it needs to turn by to point at the Waypoint provided, then how far it needs to travel
* to reach it. As a Path is split into small sections by the Java code, we can assume
* that only one rotation and forward movement are required.
*
* If the journey is successful and the robot reaches its destination, it returns the
* Waypoint it was handed. If the robot becomes blocked along its path, then the robot
* returns a Waypoint with a value representing how far it was able to travel, effectively
* its new location relative to where it started the current drive call.
*/


Waypoint Propulsion::Drive (Waypoint w){
  /**
   * atan2 values, 0 = East (Positive X axis), M_PI/2 = North (Positive Y Axis)
   *         Negative M_PI/2 = South (Negative Y Axis)
   *         M_PI = West (Negative X Axis)
   * See http://en.cppreference.com/w/cpp/numeric/math/atan2 for more.
   * Values returned are in Radians, so value * 180/pi = degrees
   *
   * This has now been factored out to datatype AngleDistance
   */
  AngleDistance movement = w.toAngleDistance();
  currentHeading += rotate(movement.getTheta()-currentHeading);
  long distance = forward(movement.getDistance());
  return Waypoint((distance/movement.getDistance()) * w.getX(), (distance/movement.getDistance()) * w.getY());
}


/**
 * Rotate method
 *
 * A correct rotation will move by the smallest amount possible to arrive at the required
 * angle. (Left if amount required to turn left is smaller than required right turn etc.)
 * To do this, the drive method subtracts the current heading from the total turn angle.
 * Total turn is calculated from 0 and the robot may never face that way, so we rotate
 * by the difference between theta and current heading.
 */

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

/**
 * Forward method
 *
 * Once the robot has rotated we can move forward.
 * Moving forward means moving outside the dimensions of the robot body (rotation
 * moves within its own length, there is no net movement forward.) so we need to be
 * aware of any returns from the Ultrasound sensors when we attempt to move forward.
 */

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


double Propulsion::getHeading(){
  return currentHeading;
}

