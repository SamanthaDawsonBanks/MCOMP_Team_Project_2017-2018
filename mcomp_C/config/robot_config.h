/*
 * robot_config.h
 *
 *  Created on: 22 Mar 2018
 *      Author: David Avery 15823926
 */

//Definition of the default baud rate for the serial connection
#ifndef CONFIG_H_
#define CONFIG_H_

#define SerialRate 115200

#define SENSOR Serial2              //board to TX2 RX2 on Mega 2560 LiDAR Sensor
const double wheelTrack = 36;  //The TOTAL distance between the center point of the wheels in cm
const double wheelSize = 47.8779;  //The circumference of the fitted wheel and tire in cm
const double stepsInRev = 200;  //The number of steps to make a full turn of the stepper

const double LiDAROffset = 0;  //The number of degress the LiDAR sensor is offset from true forward
const double LiDARReadsPerRev = 360;  //The number of degress the LiDAR sensor is offset from true forward
const double LiDAROptimalRPM = 240;  //The optimal RPM for the LiDAR motor


#endif /* CONFIG_H_ */
