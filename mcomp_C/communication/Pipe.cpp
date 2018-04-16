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

void Pipe::Decode(String s) {
  String command = "";
  String varOne = "";
  String varTwo = "";


}

/**
 * Destructor for the Pipe class.
 */
Pipe::~Pipe() {
  // TODO Auto-generated destructor stub
}

