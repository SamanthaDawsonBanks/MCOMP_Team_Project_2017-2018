/*
 * Pipe.h
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan
 */

#ifndef COMMUNICATION_PIPE_H_
#define COMMUNICATION_PIPE_H_

#include "Arduino.h"

class Pipe {
 public:
  Pipe();
  virtual ~Pipe();

  int available();
  bool write();
  //TODO overloaded write methods

  byte read();char[] decode();byte[] encode();
  void close();

 private:
  const int BAUD_RATE;

};

#endif /* COMMUNICATION_PIPE_H_ */
