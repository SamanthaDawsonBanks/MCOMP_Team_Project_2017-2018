#include "Arduino.h"
#include "Arduino.h"

/*
 * Ultrasound_Trinket_C.ino
 *
 *  Created on: 03 April 2018
 *      Author: Ryan Shoobert 15812407
 *
 * Ultrasound Sensing
 *
 * This program handles obstacle detection for the robots with the ultrasound sensors.
 * This currently allows for the checking of one sensor.
 */

// trigger distance definitions for each sensor
#define FRONT_SENSOR_TRIGGER 30
#define LEFT_RIGHT_SENSOR_TRIGGER 40 //?
#define REAR_SENSOR_TRIGGER 30

// defines pins numbers
#define ECHO_PIN 0
#define OUTPUT_LED_PIN 1
#define TRIG_PIN 2

// defines global variables
long duration = 0;
long distance = 0;

const int triggerDistance = FRONT_SENSOR_TRIGGER;

void setup() {
  pinMode(TRIG_PIN, OUTPUT);  // Sets the trigPin as an Output
  pinMode(ECHO_PIN, INPUT);   // Sets the echoPin as an Input

  pinMode(OUTPUT_LED_PIN, OUTPUT);  //led
}

void loop() {
  // Clears the trigPin
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);

  // Sets the trigPin on HIGH state for 10 micro seconds
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);

  // Reads the echoPin, returns the sound wave travel time in microseconds
  duration = pulseIn(ECHO_PIN, HIGH);

  // Calculating the distance
  distance = duration * 0.034 / 2;

  //raise the output pin HIGH if the travel time is below a threshold/an obstacle is close to the robot
  if (distance < triggerDistance) {
    digitalWrite(OUTPUT_LED_PIN, HIGH);
  } else {
    digitalWrite(OUTPUT_LED_PIN, LOW);
  }
}
