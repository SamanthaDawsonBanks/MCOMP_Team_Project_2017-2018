/*
 * LSensor.h
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "../common/datatypes/AngleDistance.h"
#include "../common/datatypes/Waypoint.h"
#include "../libraries/Adafruit_MotorShield.h"
#include <math.h>


#ifndef LSENSOR_LSENSOR_H_
#define LSENSOR_LSENSOR_H_

class LSensor {

 private:
  unsigned int targetPWM;
  Adafruit_MotorShield AFMS1;
  Adafruit_DCMotor lidarMotor;
  unsigned int* pDistances;
  byte inByte;                         //incoming byte on serial2
  unsigned int avgRPM;
  unsigned int targetRPM;

 public:
  LSensor();
  virtual ~LSensor();
  bool isGood();  //better name?
  unsigned int getAvgRPM();
  Waypoint* sense();
  unsigned int getRead(int);
  unsigned int* decodeRead();
  bool adjustRPM();
  void getEncodedRead();
  Waypoint* toWaypoint();
};

#endif /* LSENSOR_LSENSOR_H_ */
