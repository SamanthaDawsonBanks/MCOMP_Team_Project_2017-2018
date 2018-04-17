/*
 * Pipe.cpp
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 */

#include "Pipe.h"

#include "../common/datatypes/Waypoint.h"
#include "../movement/Propulsion.h"
#include "../lsensor/LSensor.h"

Pipe::Pipe() {
  //Configure and start serial connection
  Serial.begin(BAUD_RATE);
}

void Pipe::RecieveCommand() {
  String* res = new String[3];

  res = Decode(Call());

  //what did we get - do it and build a response
  if (res[0] == "COMPASS") {
    //getCompassReading
    //encodeCompass
    //send back
  } else if (res[0] == "DRIVE") {
    //drive
    //get final waypoint
    //send back
  } else if (res[0] == "LSENSE") {
    //get lidar reading
    //get lidar read array
    //send back
  }
}

String Pipe::Call() {
  String sb = "";

  while (Serial.available() == 0) {

  }

  char incomingChar = Serial.read();

  while (incomingChar != '\n') {
    while (Serial.available() == 0) {
    }

    incomingChar = Serial.read();

    if (incomingChar != '\n') {
      sb.concat(incomingChar);
    }
  }

  return sb;
}

String* Pipe::Decode(String readData) {
  String* chunks = new String[3];
  int posCounter = 0;
  int num = 0;

  for (int i = 0; i < readData.length(); i++) {
    if (readData.charAt(i) == ';') {
      chunks[num] = readData.substring(posCounter, i);
      posCounter = i + 1;
      num++;
    }
  }
  //return separate parts of the read string
  return chunks;
}

String Pipe::EncodeDouble(double d) {
  String s = String(d, 2);
  return s;
}

String Pipe::EncodeWaypoint(Waypoint w) {
  String sb = "";

  sb.concat(w.getX());
  sb.concat(';');
  sb.concat(w.getY());

  sb.concat('\n');

  return sb;
}

/**
 * Destructor for the Pipe class.
 */
Pipe::~Pipe() {
  // TODO Auto-generated destructor stub
}

