/*
 * robot_config.h
 *
 *  Created on: 22 Mar 2018
 *      Author: David Avery 15823926
 */

const double wheelTrack = 36;  //The TOTAL distance between the center point of the wheels in cm
const double wheelSize = 31.42;  //The circumference of the fitted wheel and tire in cm
const double stepsInRev = 200;  //The number of steps to make a full turn of the stepper

const double LiDAROffset = 0;  //The number of degress the LiDAR sensor is offset from true forward
const double LiDARReadsPerRev = 360;  //The number of degress the LiDAR sensor is offset from true forward
const double LiDAROptimalRPM = 240;  //The optimal RPM for the LiDAR motor
