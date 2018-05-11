/*
 * Pipe.cpp
 *
 *  Created on: 4 Apr 2018
 *      Author: Ryan Shoobert 15812407
 *
 *  Pipe Class
 *
 *  This class handles commands/data received from a sender and decodes them into
 *  operations. Currently commands exist for driving, retrieving lidar reads and
 *  Retrieving a reading from the compass. To extend, this could handle readings
 *  from different sensors and driving different kinds of motors.
 */

#include "Pipe.h"

#include "../config/robot_config.h"

Pipe::Pipe() {
}

/**
 * Receive Command Method
 *
 * Once a command has been read from the serial port and decoded, it will need
 * to be checked and if matches with a defined command, it will be executed and
 * the data that is returned will need to be encoded for returning to the sender.
 */
void Pipe::recieveCommand() {

  String* res = new String[3];
  Propulsion prop = Propulsion();
  LSensor lSen = LSensor();

  CONSOLE.begin(CONSOLERATE);
  CONSOLE.setTimeout(CONSOLETIMEOUT);

  res = decode(call());

  //check the data read and build a response if it contains any matching commands
//  if (res[0] == "COMPASS") {
//    writeString(encodeDouble(Compass().compassRead()));
//  }
  if (res[0] == "DRIVE") {
    Waypoint w = Waypoint(res[1].toDouble(), res[2].toDouble());
    writeString(encodeWaypoint(prop.Drive(w)));
  }
  if (res[0] == "LSENSE") {
    writeString(encodeLRead(lSen.sense()));
  }
}

/**
 * Call Method
 *
 * When a command is sent, it must be read in and returned for decoding.
 * Until then, as a robot will only act when instructed to do so, this
 * will act as a blocking call until input is received.
 */
String Pipe::call() {

  String sb = "";

  while (!CONSOLE.available()) {
  }

  char incomingChar = CONSOLE.read();
  sb.concat(incomingChar);

  while (incomingChar != '\n') {
    while (!CONSOLE.available()) {
    }

    incomingChar = CONSOLE.read();

    if (incomingChar != '\n') {
      sb.concat(incomingChar);
    }
  }
  return sb;
}

/**
 * Write String Method
 *
 * Owing to the fact that Arduino Serial.write can't take a string, this method
 * will handle the writing of each character in a string to the serial port.
 */
void Pipe::writeString(String s) {
  for (unsigned int i = 0; i < s.length(); i++) {
    CONSOLE.write(s.charAt(i));
  }
}

/**
 * Decode Method
 *
 * Once a command has been received from the sender, if it contains extra information,
 * Separated by a delimiter (in the case of drive an DRIVE;x;y) then this must be
 * separated into pieces. If not, then anything in the read data will be returned whole.
 */
String* Pipe::decode(String readData) {

  int delims[2];
  String* chunks = new String[3];

  delims[0] = readData.indexOf(';');
  delims[1] = readData.indexOf(';', delims[0]);

  if (delims[0] >= 0) {
    chunks[0] = readData.substring(0,delims[0]);
    chunks[1] = readData.substring(delims[0],delims[1]);
    chunks[2] = readData.substring(delims[1]);
  } else {
    chunks[0] = readData;
  }

  //return separate parts of the read string
  return chunks;
}

/**
 * Encode Double Method
 *
 * Prior to sending the response to the compass read command, the output of the
 * read must first be encoded into a format it can be sent and decoded at
 * the receiving end.
 */
String Pipe::encodeDouble(double d) {
  String sb = String(d, 2);
  sb.concat('\n');
  return sb;
}

/**
 * Encode Lidar Read Method
 *
 * As a result of the lsense command being issued/lidar read being taken, the data
 * obtained from the sensor needs to be returned. This will enable it to be added to the
 * map to aid pathfinding.
 *
 * As a read is 360 long, it needs to be iterated through and formatted in delimiter (;)
 * seperated chunks.
 *
 */
String Pipe::encodeLRead(Waypoint* reading) {
  String sb = "";
  int i;

  for (i = 0; i < 359; i++) {
    sb.concat(encodeInlineWaypoint(*(reading + i)));
  }

  sb.concat(encodeWaypoint(*(reading + i)));

  return sb;
}

/**
 * Encode Inline Waypoint Method
 *
 * As part of the encoding for a lidar read, this method takes a waypoint set in the read
 * and encodes it minus a trailing new line character.
 */
String Pipe::encodeInlineWaypoint(Waypoint w) {
  String sb = "";

  sb.concat(w.getX());
  sb.concat(';');
  sb.concat(w.getY());
  sb.concat(';');

  return sb;
}

/**
 * Encode Waypoint Method
 *
 * Prior to returning a waypoint as a result of a drive operation,
 * either the x, y for the current location or the destination waypoint will need to
 * be formatted prior to sending.
 */
String Pipe::encodeWaypoint(Waypoint w) {
  String sb = "";

  sb.concat(w.getX());
  sb.concat(';');
  sb.concat(w.getY());

  sb.concat('\n');

  return sb;
}

/**
 * Close Method
 *
 * When shutting down, this method can be called to terminate the established serial
 * connection.
 */
void Pipe::close() {
//  CONSOLE.end();
}

/**
 * Destructor for the Pipe class.
 */
Pipe::~Pipe() {
  // currently not implemented
}

