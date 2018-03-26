/*
 * mcomp_C.ino
 *
 *  Created on: 26 Nov 2017
 *      Author: David Avery 15823926
 *      Author: Stephen Pope 15836791
 */

//------------------------------------Includes-------------------------------------
// any required libraries
#include "Arduino.h"
#include "tests/tests.h"
#include "libraries/Adafruit_MotorShield.h"

//-----------------------------------Definitions-----------------------------------
// eg. #define RED 13
// or, #define ON HIGH

//------------------------Function prototypes (signatures)-------------------------
// listing signatures assists in debugging!
//eg. int readInt(String prompt); // description & warnings

//--------------------------------Global Variables---------------------------------
// Remember you ONLY have 2k system RAM
// eg. boolean personWaiting = true;

Adafruit_MotorShield* AFMS;
Adafruit_StepperMotor* leftMotor;
Adafruit_StepperMotor* rightMotor;

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
  //MotorShield Setup
  AFMS = new Adafruit_MotorShield(); //The MotorShield
  leftMotor = (*AFMS).getStepper(200,1); //360(div)1.8 degree steps, M1 and M2 on the MotorShield
  rightMotor = (*AFMS).getStepper(200, 2); //M3 and M4 on the MotorShield
  (*leftMotor).setSpeed(30); //Set speed of rotation in RPM, 100Hz * 1.8 Degrees = 1 rotation of wheel every 2 seconds, or 30 RPM.
  //TODO change to 400Hz, or 120 rpm?
  (*rightMotor).setSpeed(30);
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
