/*
 * Propulsion.h
 *
 *  Created on: 26 Mar 2018
 *      Author: Stephen Pope 15836791
 *      Author: David Avery 15823926
 */

#ifndef PROPULSION_H_
#define PROPULSION_H_

#include "..\libraries\Adafruit_MotorShield.h"
#include "..\common\datatypes\Waypoint.h"

class Propulsion {
 public:
  Propulsion();
  virtual ~Propulsion();
  Waypoint Drive(Waypoint);

  double getHeading();
  void releaseMotors();

 private:
  Adafruit_MotorShield AFMS;
  Adafruit_StepperMotor leftMotor;
  Adafruit_StepperMotor rightMotor;
  double currentHeading;
  double rotate(double);
  long forward(long);
};

#endif /* PROPULSION_H_ */
