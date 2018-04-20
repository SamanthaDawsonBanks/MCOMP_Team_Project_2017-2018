/*
 * LSensor.cpp
 *
 *  Created on: 25 Mar 2018
 *      Author: David Avery 15823926
 *      Author: Stephen Pope 15836791
 */

#include "LSensor.h"
#include "../config/robot_config.h"
byte buffer[1980];                   //buffer of bytes that takes in a packet of four reads from the LiDAR sensor
unsigned int distances[360];
Waypoint wp[360];


LSensor::LSensor() {
  pDistances = nullptr;
  inByte = 0;
  avgRPM = 0;
  targetRPM = 240;
  targetPWM = 71; //71 rpm to hit as close as we can to the target rpm of 240
  AFMS1 = Adafruit_MotorShield(0x61);
  lidarMotor = (*AFMS1.getMotor(1));
  AFMS1.begin();
  lidarMotor.setSpeed(targetPWM);
  lidarMotor.run(FORWARD);
  SENSOR.begin(115200);             //init the input from LiDAR serial2
}

LSensor::~LSensor() {
  //lidarMotor.setSpeed(0);
}


unsigned int LSensor::getAvgRPM(){
  avgRPM = 0;
  unsigned int rpmLe;            //RPM in raw Little-endian
  unsigned int rpmBe;            //RPM in converted Big-endian
  int counter = 2;
  unsigned int lowerB;     //nth byte in the buffer, lower half of 16 bit little-endian value
  unsigned int upperB;   //n + 1 byte in the buffer, upper half of 16 bit little-endian value
  for (int i; i<90; i++){
    rpmLe = 0;
    rpmBe = 0;
    lowerB = buffer[counter];
    upperB = buffer [counter+1];
    rpmLe = rpmLe | lowerB;              //rpmLe (0) bitwise OR with b2 = b2 but in a 16 bit in not 8 bit byte
    rpmLe <<= 8;                       //shift bits 8 left to make space for b3
    rpmLe = rpmLe | upperB;            //rpmLe (b2+8 zeros) logical OR with b3 = b2 concat b3 in one 16 bit value
    for(int i = 0; i < 16; i++){       //loop to flip bits and make value big endian
      rpmBe <<= 1;                     //no effect first time, makes space for incoming bit
      rpmBe |= (rpmLe & 0x0001);       //0  logical OR then set with 1 or 0 logical AND with 1. If bit is 1 then set bit to 1
      rpmLe >>= 1;                     //Shift right to get next bit in little endian value
    }
    rpmBe >>= 6;                       //Shift right 6 to remove the floating point (64th of an RPM) values
    avgRPM = avgRPM + rpmBe;
    counter = counter + 22;
  }
  avgRPM = avgRPM/90;
  return avgRPM;                      //Returns RPM as whole number
}

/*
 * A single packet contains 4 reads.
 * A single read is 4 bytes long.
 * The first 14 bits(0-13) of the first 2 bytes are the actual distance data.
 * The remaining 2 bits are error states.
 * Bit 14 shows high if the return signal was weaker than expected.
 * Bit 15 shows high if the distance could not be calculated.
 * If bit 15 is high the read should be 0 and discounted.
 * The final 2 bytes are signal strength information and not worried about here.
 * The bit data for distance is stored in little endian format. Backwards to normal.
 * It needs to be flipped in order for the JVM on the other end to work with it.
 */
unsigned int LSensor::getRead(int location){
  unsigned int le = 0;
  unsigned int be = 0;
  unsigned int lowerByte = buffer[location];
  unsigned int upperByte = buffer[location+1];
  if (upperByte & 0x01){              //Bitwise compare to see if did not calc flag was high
    return 0;
  }
  else{
    upperByte = upperByte & 0xFC;  //Removes weak signal flag
    le = le | lowerByte;
    le <<= 8;
    le = le | upperByte;
    for (int i = 0; i < 16; i++){
      be <<= 1;
      be = be | (le & 0x01);        //If the LSB in le is high, transpose to LSB on be.
      le >>= 1;
    }
    return be;
  }
}

unsigned int* LSensor::decodeRead(){
  int counter = 0;
  for(int i = 0; i < 90; i = i + 4){
    counter += 4;
    for(int j = 0;  j < 4; j++){
      distances[i*4+j] = getRead(counter);
      counter += 4;
    }
    counter = counter + 2; //To jump over the checksum bytes!
  }
  pDistances = &distances[0];
  return pDistances;
}

bool LSensor::adjustRPM(){
  getAvgRPM();
  if (avgRPM > targetRPM+20){ //if RPM is more than 10RPM off target, adjust it
    targetPWM = targetPWM--;
    lidarMotor.setSpeed(targetPWM);
    lidarMotor.run(FORWARD);
    return false;
  }
  else if (avgRPM < targetRPM-20){
    targetPWM = targetPWM++;
    lidarMotor.setSpeed(targetPWM);
    lidarMotor.run(FORWARD);
    return false;
  }
  else return true;
}


void LSensor::getEncodedRead(){
  if (SENSOR.available()){
    inByte = SENSOR.read();
  }
  if(inByte == 0xFA){            //Read a byte from Serial
    for(int i = 0; i < 1980; i++){//The head of a LiDAR packet read
      buffer[i] = SENSOR.read();  //Read the next bit in the serial and write it to next position in buffer
    }
  }
}

Waypoint* LSensor::toWaypoint(){
  //distance is 14bits
  //distance is in mm so max =16,383mm
  //convert to cm so dave can just div by 5 to make grid squares
  //so div by 10
  //then pos is array is theta, so the we can calc the y value using trig
  //the hyp and the opp gives us 2 sides of a triangle so we can SOHCAHTOA to find x
  //Waypoint constructor does this when handed an AngleDistance
  for (int i = 0; i < 360; i++){
    AngleDistance ad = AngleDistance ((double) i, (long) (*(pDistances+i)/10));
    Waypoint a  = Waypoint(ad);
    wp[i] = a;
  }
  Waypoint* wpPtr = &wp[0]; //point to head of WP array
  return wpPtr;
}

Waypoint* LSensor::sense(){
  getEncodedRead(); //So we can dig out an accurate avgRPM
  while(adjustRPM() == false){ //Keep adjusting RPM until within 10 of target
    getEncodedRead();
    adjustRPM();
  }
  getEncodedRead(); //The proper read
  decodeRead();//Reverse reads so they are BigEndian and return pointer to head of array
  return toWaypoint();
}

Waypoint* LSensor::lSensorTest(){
  buffer = {}; //TODO fill with LiDAR return for first mapLayer
  decodeRead();
  return toWaypoint();
}

