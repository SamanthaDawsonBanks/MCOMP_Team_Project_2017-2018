/*
 * Pipe.cpp
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan
 */

#include "Pipe.h"

const int BAUD_RATE = 9600;

Pipe::Pipe() {
  // TODO Auto-generated constructor stub
  //Serial.begin(BAUD_RATE);
}

int available() {
  return 0;
}

bool write(/*PARAMS*/) {
  //Serial writes

  return false;
}

byte read() {
  //Serial reads
}

char[] decode() {

}

byte[] encode() {

}

void closePipe() {
  //serial.end?? = may not use
}

Pipe::~Pipe() {
  // TODO Auto-generated destructor stub
}

