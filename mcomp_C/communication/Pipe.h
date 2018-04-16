/*
 * Pipe.h
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 */

#ifndef COMMUNICATION_PIPE_H1_
#define COMMUNICATION_PIPE_H1_

#include "Arduino.h"
#include "../common/datatypes/Waypoint.h"

#define BAUD_RATE 115200

class Pipe {
 public:
  Pipe();
  virtual ~Pipe();

  String recieve();

  void close();

 private:
  Waypoint decodeDrive(String);
  String encode(String s)
};

#endif /* COMMUNICATION_PIPE_H1_ */
