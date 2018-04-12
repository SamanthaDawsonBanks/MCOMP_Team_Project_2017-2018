#include "Arduino.h"

#define CONSOLE Serial               //usb to console
#define CHANNEL Serial2              //board to board TX2 RX2 on Mega 2560
byte inByte;                         //incoming byte on serial2
byte buffer[23];                     //buffer of bytes that takes in a packet of four reads from the LiDAR sensor

void setup()                         //The setup function is called once at startup of the sketch
{
  CONSOLE.begin(115200);             //init the output serial
  CHANNEL.begin(115200);             //init the input from LiDAR serial2
}

unsigned int getRPM(){
  unsigned int rpmLe = 0;            //RPM in raw Little-endian
  unsigned int rpmBe = 0;            //RPM in converted Big-endian
  unsigned int bTwo = buffer[2];     //3rd byte in the buffer, lower half of 16 bit little-endian value
  unsigned int bThree = buffer[3];   //4th byte in the buffer, upper half of 16 bit little-endian value
  CONSOLE.println(bTwo,BIN);         //for checking
  CONSOLE.println(bThree,BIN);

  rpmLe = rpmLe | bTwo;              //rpmLe (0) bitwise OR with b2 = b2 but in a 16 bit in not 8 bit byte
  rpmLe <<= 8;                       //shift bits 8 left to make space for b3
  rpmLe = rpmLe | bThree;            //rpmLe (b2+8 zeros) logical OR with b3 = b2 concat b3 in one 16 bit value
  for(int i = 0; i < 16; i++){       //loop to flip bits and make value big endian
    rpmBe <<= 1;                     //no effect first time, makes space for incoming bit
    rpmBe |= (rpmLe & 0x0001);       //0  logical OR then set with 1 or 0 logical AND with 1. If bit is 1 then set bit to 1
    rpmLe >>= 1;                     //Shift right to get next bit in little endian value

  }
  rpmBe >>= 6;                       //Shift right 6 to remove the floating point (64th of an RPM) values
  return rpmBe;                      //Returns RPM as whole number
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
unsigned int getRead(){
  unsigned int le = 0;
  unsigned int be = 0;
  unsigned int lowerByte = buffer[4];
  unsigned int upperByte = buffer[5];
  if (upperByte & 0x01){
    return 0;
  }
  else{
    le = le | lowerByte;
    le <<= 8;
    le = le | upperByte;
    for (int i = 0; i < 16; i++){
      be <<= 1;
      be = be | (le & 0x01);
      le >>= 1;
    }
    return be;
  }
}

void loop()
{
  if (CHANNEL.available()) {
    inByte = CHANNEL.read();         //Read a byte from Serial
    if(inByte == 0xFA){              //The head of a LiDAR packet read
      for(int i = 0; i < 22; i++){
        buffer[i] = CHANNEL.read();  //Read the next bit in the serial and write it to next position in buffer
      }
      CONSOLE.println(getRPM(),DEC);  //Print RPM in decimal
    }
  }
}


