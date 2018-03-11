/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *
 */

//------------------------------------Includes-------------------------------------
// any required libraries
#include "Arduino.h"
#include "common/datatypes/path/Path.h"

//-----------------------------------Definitions-----------------------------------
// eg. #define RED 13
// or, #define ON HIGH

//------------------------Function prototypes (signatures)-------------------------
// listing signatures assists in debugging!
//eg. int readInt(String prompt); // description & warnings

//--------------------------------Global Variables---------------------------------
// Remember you ONLY have 2k system RAM
// eg. boolean personWaiting = true;

//----------------------------Global Volatile Variables----------------------------
// Volatile variables used by interrupts
// eg. volatile boolean personWaiting = true; // volatile interrupt

//--------------------------------------Setup--------------------------------------
//The setup function is called once at startup of the sketch
// Remember to "Serial.begin(baud);"
// and Initialise I/O pins
// eg. pinMode(RED, OUTPUT);

void setup() {
  // Add your initialisation code here
  // TODO method stub
  testWaypoint();
  testPath();
}  //End Setup

//-----------------------------Functions / Subroutines-----------------------------

//---------------------------ASM Functions / Subroutines---------------------------

//--------------------------------Interrupt Handlers-------------------------------

//-----------------------------------Loop / Main-----------------------------------
// The loop function is called in an endless loop

void loop() {
  // Add your repeated code here
  // TODO method stub
}  // End Loop

//--------------------------------------Tests--------------------------------------
//TODO pull these out into a separate file?
void testPath() {
  Serial.begin(9600);
  delay(1000);
  Serial.println("Begin testPath");

  Waypoint q;

  Waypoint d(100, 100);
  Path p(d);

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  p.addNode(Waypoint(200, 200));

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  p.addNode(Waypoint(250, 250));
  p.addNode(Waypoint(350, 350));

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
      Waypoint x(i, j);
      p.addNode(x);
    }
  }

  for (int k = 0; k < 10; k++) {
    for (int l = 0; l < 10; l++) {
      q = p.poll();
      Serial.print(q.getX());
      Serial.print(",");
      Serial.print(q.getY());
      Serial.println("");
    }
  }

  Serial.println("end of data");
  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  q = p.poll();
  Serial.print(q.getX());
  Serial.print(",");
  Serial.print(q.getY());
  Serial.println("");

  Serial.println("End testPath");
}

void testWaypoint() {
  Serial.begin(9600);
  delay(1000);
  Serial.println("Begin testWaypoint");

  Waypoint t(0, 0);  // = new Waypoint(0,0);
  Serial.println("end of data input");
  Serial.print(t.getX());
  Serial.print(",");
  Serial.print(t.getY());
  Serial.println("");

  Serial.println("End testWaypoint");
}
