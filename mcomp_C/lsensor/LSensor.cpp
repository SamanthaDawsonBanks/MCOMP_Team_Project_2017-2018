/*
 * LSensor.cpp
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "LSensor.h"

LSensor::LSensor() {
  currentPWM = 0;
  targetPWM = 71; //71 rpm to hit as close as we can to the target rpm of 240
  AFMS1 = Adafruit_MotorShield(0x61);
  lidarMotor = (*AFMS1.getMotor(1));
  AFMS1.begin();
  lidarMotor.setSpeed(targetPWM);
  lidarMotor.run(FORWARD);
}

LSensor::~LSensor() {
  lidarMotor.setSpeed(0);
}

int getRPM(){
  return 0;
}

int manageSpeed(){
  return 0;
}

void takeRead() {
  //call for LiDAR
  //sync to SPIN
  //store LiDAR data
  //decode packets
  //convert from AD[] to WP[]
  //return list

}

AngleDistance * readLiDAR() {
  // TODO Auto-generated stub

  //Loop through packets

  for (int pCount = 0; pCount < 90; pCount++) {

    //loop through reads

    for (int rCount = 0; rCount < 4; pCount++) {

      //grabDATA

      //format is: <start> <index> <speed_L> <speed_H> [Data 0] [Data 1] [Data 2] [Data 3] <checksum_L> <checksum_H>

    }

  }

  return nullptr;
}

//some form of checksum method

//some form of decode method

//loop for AD>WP



