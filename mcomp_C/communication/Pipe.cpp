/*
 * Pipe.cpp
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 */

#include "Pipe.h"

//imports for function mapping
#include <functional>
#include <map>
//#include <string> does String not come in arduino.h??
//^^may be factored out pending outcome of code^^

const int BAUD_RATE = 9600;

Pipe::Pipe() {
  //Configure and start serial connection
  Serial.begin(BAUD_RATE);
}

//More of a wrapper method at this stage
int available() {
  return Serial.available();
}

bool write(/*PARAMS*/) {
  //Serial writes
  Serial.write(/*PARMAS*/);

  return false;
}

byte read() {
  //Serial reads
  Serial.read();

  //as an evil note for potential command triggering
  //std::map<std::string, std::function<>> methodMap; //would be at top of class I believe so that at this point it can be referenced instead of created every time

}

String decode() {

}

byte[] encode() {

}

void closePipe() {
  //serial.end?? = may not use but does present itself nicely from a cleanup point of view
  Serial.end();
}

/**
 * Destructor for the Pipe class.
 */
Pipe::~Pipe() {
  // TODO Auto-generated destructor stub
  delete BAUD_RATE;
}

