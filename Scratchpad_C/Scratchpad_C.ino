#include "Arduino.h"

#define CONSOLE Serial //usb to console
#define CHANNEL Serial1 //board to board TX1 RX1 on Mega 2560
byte inByte;
byte buffer [22];

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

void loop()
{
  if (CHANNEL.available()) {
    inByte = CHANNEL.read();
    if(inByte == 0xFA){
      buffer [0] = 0xFA;
      CONSOLE.println (0xFA);
      CONSOLE.println("Start Packet");
      for(int i = 1; i < 22; i++){
        buffer [i] = CHANNEL.read();
        CONSOLE.print(buffer[i]);
        CONSOLE.println(" ");
      }
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


