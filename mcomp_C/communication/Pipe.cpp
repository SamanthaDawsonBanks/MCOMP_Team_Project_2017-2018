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

LSensor ls;
Propulsion p;

Pipe::Pipe() {
  //Configure and start serial connection
  Serial.begin(BAUD_RATE);
}

void Pipe::recieveCommand() {
  String* res = new String[3];

  res = decode(call());

  //what did we get - do it and build a response
  if (res[0] == "COMPASS") {
    //Serial.write(encodeDouble(compassSense()));
  } else if (res[0] == "DRIVE") {
    Waypoint w = Waypoint(res[1].toDouble(), res[2].toDouble());

    Serial.write(encodeWaypoint(p.Drive(w)));
  } else if (res[0] == "LSENSE") {
    Serial.write(encodeLRead(ls.takeRead()));
  }
}

String Pipe::call() {
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

String* Pipe::decode(String readData) {
  String* chunks = new String[3];
  int posCounter = 0;
  int num = 0;

  for (int i = 0; i < readData.length(); i++) {
    if (readData.charAt(i) == ';') {
      chunks[num] = readData.substring(posCounter, i);
      posCounter = i + 1;
      num++;
    }

    chunks[num] = readData.substring(posCounter, i);
  }
  //return separate parts of the read string
  return chunks;
}

String Pipe::encodeDouble(double d) {
  String s = String(d, 2);
  return s;
}

String Pipe::encodeLRead(Waypoint* reading) {
  String sb = "";
  int i;

  for (i = 0; i < 359; i++) {
    sb.concat(encodeInlineWaypoint(*(reading + i)));
  }

  sb.concat(encodeWaypoint(*(reading + i)));

  return sb;
}

String Pipe::encodeInlineWaypoint(Waypoint w) {
  String sb = "";

  sb.concat(w.getX());
  sb.concat(';');
  sb.concat(w.getY());
  sb.concat(';');

  return sb;
}

String Pipe::encodeWaypoint(Waypoint w) {
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

