/*
 * Pipe.h
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 */

#ifndef PIPE_H_
#define PIPE_H_

//library includes
#include "Arduino.h"
#include "../common/datatypes/Waypoint.h"
#include "../lsensor/LSensor.h"
#include "../movement/Propulsion.h"

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
  void writeLRead(Waypoint reading[]);

  void close();
};

#endif /* PIPE_H_ */
