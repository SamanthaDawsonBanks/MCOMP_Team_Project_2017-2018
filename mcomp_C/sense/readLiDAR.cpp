/*
 * readLiDAR.cpp
 *
 *  Created on: 24 Mar 2018
 *      Author: David Avery 15823926
 */

#include "readLiDAR.h"
#include "Arduino.h"

AngleDistance * readLiDAR() {
  // TODO Auto-generated stub

  //sync to SPIN

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

