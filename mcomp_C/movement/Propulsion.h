/*
 * Propulsion.h
 *
 *  Created on: 26 Mar 2018
 *      Author: Steve
 */

#ifndef MOVEMENT_PROPULSION_H_
#define MOVEMENT_PROPULSION_H_
#include "libraries/Adafruit_MotorShield.h"

class Propulsion {
 public:
  Propulsion();
  virtual ~Propulsion();

 private:
  Adafruit_MotorShield AFMS;
};

#endif /* MOVEMENT_PROPULSION_H_ */
