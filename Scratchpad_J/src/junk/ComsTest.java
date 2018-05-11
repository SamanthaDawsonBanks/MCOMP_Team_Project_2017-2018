package junk;

import java.io.IOException;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class ComsTest {

  private static SerialPort p;

  public static void main(String[] args) {



    // getting serial ports list into the array
    String[] portNames = SerialPortList.getPortNames();

    if (portNames.length == 0) {
      System.out.println(
          "There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
      System.out.println("Press Enter to exit...");
      try {
        System.in.read();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return;
    }

    for (int i = 0; i < portNames.length; i++) {
      System.out.println(portNames[i]);
    }

    p = new SerialPort(portNames[0]);
      try {
        p.openPort();
        p.setParams(9600, 8, 1, 0);
      } catch (SerialPortException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }


    while (true) {
      try {
        p.writeInt('H');
        Thread.sleep(2000);
        p.writeInt('L');
        Thread.sleep(1000);

      } catch (SerialPortException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
