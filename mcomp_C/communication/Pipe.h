/*
 * Pipe.h
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 */

#ifndef COMMUNICATION_PIPE_H1_
#define COMMUNICATION_PIPE_H1_

//library includes
#include "Arduino.h"
#include "../common/datatypes/Waypoint.h"
#include "../movement/Propulsion.h"
#include "../lsensor/LSensor.h"

//Definition of the default baud rate for the serial connection
#define BAUD_RATE 115200

class Pipe {
 public:
  Pipe();
  virtual ~Pipe();

  String call();

  void recieveCommand();
  void writeString(String s);

  String* decode(String readData);

  String encodeInlineWaypoint(Waypoint w);
  String encodeWaypoint(Waypoint w);
  String encodeDouble(double d);
  String encodeLRead(Waypoint reading[]);

  void close();
};

#endif /* COMMUNICATION_PIPE_H1_ */
