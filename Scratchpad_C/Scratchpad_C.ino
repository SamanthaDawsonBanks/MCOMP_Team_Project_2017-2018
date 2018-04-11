#include "Arduino.h"

#define CONSOLE Serial //usb to console
#define CHANNEL Serial1 //board to board TX1 RX1 on Mega 2560
byte inByte;
byte buffer[23];

//The setup function is called once at startup of the sketch
void setup()
{
  CONSOLE.begin(115200);
  CHANNEL.begin(115200);
}

unsigned int packetToNumber(String packet){
  unsigned int res;
  res = (packet.charAt(0));

  return res;
}

unsigned int getRPM(){
  unsigned int rpmLe = 0;
  unsigned int rpmBe = 0;
  rpmLe = rpmLe | buffer[2];
  rpmLe = rpmLe << 8;
  rpmLe = rpmLe | buffer[3];
  for(int i = 0; i < 16; i++){
    rpmBe = rpmBe << 1;
    rpmBe = rpmBe | (rpmLe & 0000000000000001);
  }
  rpmBe = rpmBe >> 6;
  return rpmBe;
}

void loop()
{
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
     CONSOLE.println(getRPM());
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


