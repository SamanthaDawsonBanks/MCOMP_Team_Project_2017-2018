package junk;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ComsTest {
  
  private static SerialPort p;

  public static void main(String[] args) {
    while(true) {
      try {
        p = new SerialPort("ttyACM0");
        p.openPort();
        p.setParams(9600, 8, 1, 0);
        
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
