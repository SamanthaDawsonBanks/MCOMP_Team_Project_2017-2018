/*
 * Pipe.cpp
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 */

#include "Pipe.h"

Pipe::Pipe() {
  //Configure and start serial connection
  Serial.begin(BAUD_RATE);
}

void Pipe::RecieveCommand() {
  String* res = new String[3];

  res = Decode(Call());

  if (res[0] == "COMPASS") {
    //getCompassReading
  } else if (res[0] == "DRIVE") {
    //drive
  } else if (res[0] == "LSENSE") {
    //get lidar reading
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

/**
 * Destructor for the Pipe class.
 */
Pipe::~Pipe() {
  // TODO Auto-generated destructor stub
}

