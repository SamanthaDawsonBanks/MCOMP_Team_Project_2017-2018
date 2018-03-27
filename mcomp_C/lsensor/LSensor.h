/*
 * LSensor.h
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "../common/datatypes/AngleDistance.h"

#ifndef LSENSOR_LSENSOR_H_
#define LSENSOR_LSENSOR_H_

class LSensor {

 private:
  int currentPWM;
  int currentRPM;
  //some form of state?
  //shared buffer or data being passed?
  //incoming buffer? object or in method? UART?

  void sync();  //blocking or ready?

  void storeLiDAR();  //method or buff?

  void decodeReturn();  //90 packets to 360 AD reads //also RPM data and error data

  void convertRetrun();  //method or just part of decode? AD>rWP

 public:
  LSensor();
  virtual ~LSensor();

  bool isGood();  //better name?

  void processCommand();  //do we need?

  void takeRead();  //overall?

};

#endif /* LSENSOR_LSENSOR_H_ */
