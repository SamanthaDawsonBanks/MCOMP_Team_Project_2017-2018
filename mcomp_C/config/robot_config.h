/*
 * robot_config.h
 *
 *  Created on: 22 Mar 2018
 *      Author: David Avery 15823926
 */

//Definition of the default baud rate for the serial connection
#ifndef CONFIG_H_
#define CONFIG_H_

#define CONSOLE Serial //Serial comms via pins 0 and 1 and also USB
#define CONSOLERATE 115200
#define CONSOLETIMEOUT 2000

#define SENSOR Serial1 //Serial comms via pins 18 and 19 for LSensor
#define SENSORRATE 115200
#define SENSORTIMEOUT 2000

#define DEBUG Serial //Serial comms via pins 14 and 15 for debug spam
#define DEBUGRATE 115200
#define DEBUGTIMEOUT 2000

const double wheelTrack = 36;  //The TOTAL distance between the center point of the wheels in cm
const double wheelSize = 47.8779;  //The circumference of the fitted wheel and tire in cm
const double stepsInRev = 200;  //The number of steps to make a full turn of the stepper

const double LiDAROffset = 0;  //The number of degress the LiDAR sensor is offset from true forward
const double LiDARReadsPerRev = 360;  //The number of degress the LiDAR sensor is offset from true forward
const double LiDAROptimalRPM = 240;  //The optimal RPM for the LiDAR motor

#endif /* CONFIG_H_ */
