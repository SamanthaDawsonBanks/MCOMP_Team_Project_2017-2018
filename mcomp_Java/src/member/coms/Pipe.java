package member.coms;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

// encoder/decoder imports
import java.util.Base64;
// serial communication library import

import com.fazecast.jSerialComm.*;
import common.datatypes.Waypoint;

/**
 * 
 * 
 * @author Ryan Shoobert 15812407
 */
public class Pipe {

  private SerialPort p;
  private String comPortName;
  private final int BAUD_RATE = 9600;

  private PrintWriter comWriter;
  private Scanner comReader;

  private ArrayList<Byte> buffer = new ArrayList<Byte>();

  /**
   * DOCME
   * 
   * @param comPortName
   */
  public Pipe(String comPortName) {
    // Set up serial connection bits

    this.comPortName = comPortName;

    // will need to be more robust way of being certain about this but will assume
    // on my system for now to be COM3
    p = SerialPort.getCommPort(this.comPortName);

    // assume default baud rate for arduino board
    p.setBaudRate(BAUD_RATE);

    // may factor into separate method(s) with additional checks or failure - will
    // work on this
    p.openPort();
  }

  /**
   * Checks that status of the serial buffer
   * 
   * @return 0 if the serial is empty; > 0 if there is still data waiting to be processed
   */
  public int available() {
    // what does buffer.size() return

    throw new UnsupportedOperationException("This method is not implemented yet!");
  }

  public void send(String s) {
    byte[] encodedInput = encode(s);

    p.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

    // p.getOutputStream(); when connected to something will be for sending data out
    comWriter = new PrintWriter(p.getOutputStream());

    for (byte b : encodedInput) {
      comWriter.print(b);
    }

    // cleanup
    comWriter.flush();
    comWriter.close();
  }

  public String read() {
    p.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

    // p.getInputStream(); when connected to something will get whatever is coming
    // in
    comReader = new Scanner(p.getInputStream());

    while (comReader.hasNext()) {
      buffer.add(comReader.nextByte());
    }

    //cleanup
    comReader.close();

    byte[] readData = new byte[buffer.size()];

    for (int i = 0; i < readData.length; i++) {
      readData[i] = (byte) buffer.get(i);
      
      //clear from buffer once byte has been moved
      buffer.remove(i);
    }

    return decode(readData);
  }

  // Will also for now be private as no need for outside access??

  /**
   * 
   * @param methodName The data to be encoded into Base64
   * @return The encoded version of the input as a string
   */
  private byte[] encode(String methodName) {
    byte[] encodedOutput = Base64.getEncoder().encode(methodName.getBytes());
    return encodedOutput;
  }
  
  private byte[] encode(String methodName, Waypoint w) {
    
    //probably call normal encode for methodName
    //will need to research sending objects - may just have to get x and y out of 'w' and encode
    
    throw new UnsupportedOperationException("Overloaded version of this method not implemented yet!");
  }

  /**
   * 
   * @param input The incoming data to be
   * @return
   */
  private String decode(byte[] input) {
    byte[] decodedBytes = Base64.getDecoder().decode(input);
    String decodedOutput = new String(decodedBytes);
    return decodedOutput;
  }

  // Good practice to cleanup behind you but unsure of exactly where it will slot in yet
  public void closePipe() {
    p.closePort();
  }
}
