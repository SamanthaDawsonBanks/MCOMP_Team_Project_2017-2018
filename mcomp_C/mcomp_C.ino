/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *      Author: Stephen Pope 15836791
 *
 */

//------------------------------------Includes-------------------------------------
// any required libraries
#include "Arduino.h"
#include "tests/tests.h"

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
