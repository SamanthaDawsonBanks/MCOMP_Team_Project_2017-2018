/*
 * Pipe.h
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 */

#ifndef COMMUNICATION_PIPE_H_
#define COMMUNICATION_PIPE_H_

#include "Arduino.h"

class Pipe {
 public:
  Pipe();
  virtual ~Pipe();

  int available();
  bool write(String toWrite);
  //TODO overloaded write methods

  byte[] read();
  String decode(byte[] readBytes);
  void encode(String s);
  void close();

 private:
  const int BAUD_RATE;

};

#endif /* COMMUNICATION_PIPE_H_ */
