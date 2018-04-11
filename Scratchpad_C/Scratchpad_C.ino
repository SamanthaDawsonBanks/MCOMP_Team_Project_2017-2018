#include "Arduino.h"

#define CONSOLE Serial //usb to console
#define CHANNEL Serial2 //board to board TX1 RX1 on Mega 2560
byte inByte;
byte buffer[23];

//The setup function is called once at startup of the sketch
void setup()
{
  CONSOLE.begin(115200);
  CHANNEL.begin(115200);
  pinMode(12,OUTPUT);
  pinMode(11,OUTPUT);
  pinMode(10,OUTPUT);
}

unsigned int packetToNumber(String packet){
  unsigned int res;
  res = (packet.charAt(0));

  return res;
}

unsigned int getRPM(){
  unsigned int rpmLe = 0; //RPM in raw Little-endian
  unsigned int rpmBe = 0; //RPM in converted Big-endian
    unsigned int bTwo = buffer[2]; //3rd byte in the buffer, lower half of 16 bit little-endian value
    unsigned int bThree = buffer[3];
      CONSOLE.println(bTwo,BIN);
      CONSOLE.println(bThree,BIN);

  rpmLe = rpmLe | bTwo;
  rpmLe <<= 8;
  rpmLe = rpmLe | bThree;
  for(int i = 0; i < 16; i++){
    rpmBe <<= 1;
    rpmBe |= (rpmLe & 0x0001);
    rpmLe >>= 1;

  }
  rpmBe >>= 6;
  return rpmBe;
}

void loop()
{
  analogWrite(12, 255);
  analogWrite(11, 255);
  analogWrite(10, 255);
  /*  if(CHANNEL.available()){
      CONSOLE.print(CHANNEL.read(), HEX);
    }*/
  if (CHANNEL.available()) {
    inByte = CHANNEL.read();
    if(inByte == 0xFA){
   //   CONSOLE.println("Start Packet");
      for(int i = 0; i < 22; i++){
        buffer[i] = CHANNEL.read();
       // CONSOLE.print(buffer[i],HEX);
      }
//      CONSOLE.println(" ");
//      CONSOLE.println(buffer[1],HEX);
//      CONSOLE.println(" ");
//      CONSOLE.print(buffer[2],BIN);
//      CONSOLE.println(buffer[3],BIN);
//      CONSOLE.println(" ");

      /*for(int i = 2; i > 0; i--){
        rpm = rpm << 1;
        rpm = rpm | bitRead(buffer[3], i);
      }*/
     CONSOLE.println(getRPM(),DEC);

      //  CONSOLE.print(buffer[0]);
      //  CONSOLE.print(" : ");
      //  CONSOLE.println(buffer[1]);
    }
  }
  // CONSOLE.println(CHANNEL.read(),HEX);
  // CONSOLE.write(CHANNEL.read()); //if there is something in the channel buffer read it, print it to the console, and loop
  //}

  //if (CONSOLE.available()) {
  //  CHANNEL.write(CONSOLE.read()); //if there is something in the console buffer read it, send it to the channel, and loop
  //}
}


