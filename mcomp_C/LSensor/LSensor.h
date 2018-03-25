/*
 * LSensor.h
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "AngleDistance.h"

#ifndef LSENSOR_LSENSOR_H_
#define LSENSOR_LSENSOR_H_

class LSensor {

 private:
  int currentPWM;
  int currentRPM;
  //some form of state?
  //shared buffer or data being passed?

  void sync(); //blocking or ready?

  void storeLiDAR(); //method or buff?

  void decodeReturn();

  void convertRetrun(); //method or just part of?


 public:
  LSensor();
  virtual ~LSensor();

  bool isGood(); //better name?

  void processCommand(); //do we need?

  void takeRead(); //overall?

};

#endif /* LSENSOR_LSENSOR_H_ */
