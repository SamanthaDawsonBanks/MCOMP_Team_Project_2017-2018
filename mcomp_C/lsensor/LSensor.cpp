/*
 * LSensor.cpp
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 */

#include "LSensor.h"

LSensor::LSensor() {
  // TODO Auto-generated constructor stub

}

LSensor::~LSensor() {
  // TODO Auto-generated destructor stub
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

