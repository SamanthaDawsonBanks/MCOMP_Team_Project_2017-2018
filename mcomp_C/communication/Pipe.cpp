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

int available() {
  return Serial.available();
}

bool write(byte b) {
  return Serial.write(b);
}

String write(String toWrite) {
  //write data to the serial port

  //while something is available
  //read character
  //if == '\n' break
  //otherwise add character to String

  //return what we got back
}

byte read() {
  //Serial read
  return Serial.read();
}

String* decode() {
  //copy implementation in and mod sigs
}

String encode() {
  //copy implementation in and mod sigs
}

void closePipe() {
  //presents itself nicely from a cleanup point of view
  Serial.end();
}

/**
 * Destructor for the Pipe class.
 */
Pipe::~Pipe() {
  // TODO Auto-generated destructor stub
}

