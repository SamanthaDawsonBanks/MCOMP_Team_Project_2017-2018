/*
 * LSensor.h
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "../common/datatypes/AngleDistance.h"
#include "../common/datatypes/Waypoint.h"
#include "../libraries/Adafruit_MotorShield.h"


#ifndef LSENSOR_LSENSOR_H_
#define LSENSOR_LSENSOR_H_

class LSensor {

 private:
  int targetRPM;
  int currentRPM;
  Adafruit_MotorShield AFMS;
  Adafruit_DCMotor lidarMotor;
  //some form of state?
  //shared buffer or data being passed?
  //incoming buffer? object or in method? UART?

  void syncAndStoreLiDAR();  //blocking or ready? //NOTE this will return a pointer to the top of a 90 element array

  AngleDistance decodeReturn();  //90 packets to 360 AD reads //also RPM data and error data //NOTE this will return a pointer to the top of a 360 element array

  Waypoint convertReturn();  //method or just part of decode? AD>rWP //NOTE this will return a pointer to the top of a 360 element array

 public:
  LSensor();
  virtual ~LSensor();

  bool isGood();  //better name?

  void processCommand();  //do we need?

  Waypoint takeRead();  //overall? //NOTE this will return a pointer to the top of a 360 element array

};

#endif /* LSENSOR_LSENSOR_H_ */
