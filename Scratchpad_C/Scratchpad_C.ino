#include "Arduino.h"

#define CONSOLE Serial //usb to console
#define CHANNEL Serial1 //board to board TX1 RX1 on Mega 2560
byte inByte;
String buffer;

//The setup function is called once at startup of the sketch
void setup()
{
  CONSOLE.begin(115200);
  CHANNEL.begin(115200);
}

void loop()
{
  if (CHANNEL.available()) {
    inByte = CHANNEL.read();
    if(inByte == 0xFA){
      buffer = 0xFA;
      for(int i = 0; i < 22; i++){
        buffer += CHANNEL.read();
      }
      CONSOLE.println(buffer);
    }
  }
   // CONSOLE.println(CHANNEL.read(),HEX);
   // CONSOLE.write(CHANNEL.read()); //if there is something in the channel buffer read it, print it to the console, and loop
  //}

  //if (CONSOLE.available()) {
  //  CHANNEL.write(CONSOLE.read()); //if there is something in the console buffer read it, send it to the channel, and loop
  //}
}


