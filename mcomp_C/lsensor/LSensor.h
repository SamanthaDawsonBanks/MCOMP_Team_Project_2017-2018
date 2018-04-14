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
#define SENSOR Serial2              //board to board TX2 RX2 on Mega 2560

class LSensor {

 private:
  int targetPWM;
  int currentPWM;
  Adafruit_MotorShield AFMS1;
  Adafruit_DCMotor lidarMotor;
  unsigned int* pDistances;
  byte inByte;                         //incoming byte on serial2
  int avgRPM;
  void syncAndStoreLiDAR();  //blocking or ready? //NOTE this will return a pointer to the top of a 90 element array
  AngleDistance decodeReturn();  //90 packets to 360 AD reads //also RPM data and error data //NOTE this will return a pointer to the top of a 360 element array
  Waypoint convertReturn();  //method or just part of decode? AD>rWP //NOTE this will return a pointer to the top of a 360 element array

 public:
  LSensor();
  virtual ~LSensor();
  bool isGood();  //better name?
  unsigned int getRPM(int);
  Waypoint* sense();
  unsigned int getRead(int);
  unsigned int* getCompleteRead();
  bool adjustRPM();

};

#endif /* LSENSOR_LSENSOR_H_ */
