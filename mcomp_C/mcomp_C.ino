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
void testPath(){
  Serial.begin(9600);
 // Waypoint * d = new Waypoint(0,0);
//  Path p = new Path(d);
//  for (int i = 0; i <= 10; i++) {
//    for (int j = 0; j <= 10; j++) {
//      Waypoint w = Waypoint(i,j);
//      p.addNode(w);
//    }
//  }
//
//  for (int i = 0; i <= 10; i++) {
//    for (int j = 0; j <= 10; j++) {
//      Serial.print(p.poll().getX() + " , " + p.poll().getY());
//    }
//  }

  Serial.println("end of data");
//  shared_ptr<Path> x = make_shared<Path>();
//  Serial.println(p.poll().getX() + " , " + p.poll().getY());
//  Serial.println(p.poll().getX() + " , " + p.poll().getY());
//  Serial.println(p.poll().getX() + " , " + p.poll().getY());
//  Serial.println(p.poll().getX() + " , " + p.poll().getY());
//  Serial.println(p.poll().getX() + " , " + p.poll().getY());

}
