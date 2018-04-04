package member.coms;

// general imports
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import common.datatypes.Waypoint;
// encoder/decoder imports
import java.util.Base64;
// serial communication library import
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

/**
 * A communication class for handling...
 * 
 * @author Ryan Shoobert 15812407
 */
public class Pipe {

  private SerialPort p;
  private String comPortName;

  // Connection Parameters - Should never change these once Pipe instance created
  private final int BAUD_RATE = 9600;
  private final int NUM_OF_DATA_BITS = 8;
  private final int NUM_OF_STOP_BITS = 2;
  private final int NUM_OF_PARITY_BITS = 1;

  /**
   * DOCME
   * 
   * @param comPortName
   */
  public Pipe(String comPortName) {
    this.comPortName = comPortName;

    try {
      // will need to be more robust way of being certain about this but will assume
      // on my system for now to be COM3
      p = new SerialPort(comPortName);

      // may factor into separate method(s) with additional checks or failure - will work on this
      p.openPort();

      // assume default baud rate for Arduino board, eight data bits, two stop bits, 1 bit for
      // parity
      p.setParams(BAUD_RATE, NUM_OF_DATA_BITS, NUM_OF_STOP_BITS, NUM_OF_PARITY_BITS);
    } catch (SerialPortException e) {
      // TODO Deal with a bad serial port setup
      e.printStackTrace();
    }
  }

  /**
   * Checks that status of the serial buffer
   * 
   * @return 0 if the serial is empty; > 0 if there is still data waiting to be processed
   * @throws SerialPortException
   */
  public int available() throws SerialPortException { // maybe one of those unfortunate examples of
                                                      // where we have to pass it out
    // return p.getInputBufferBytesCount();

    throw new UnsupportedOperationException("This method is not implemented yet!");
  }

  public void write(byte toWrite[]) {
    byte[] encodedBytes = encode(toWrite);

    try {
      for (byte b : encodedBytes) {
        // write data to connected port
        p.writeByte(b);
      }

    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public byte[] read() {
    byte readData[];

    try {
      // create array the size of the incoming input and read the bytes into it
      readData = new byte[p.getInputBufferBytesCount()];
      readData = p.readBytes();

      return decode(readData);
    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      // Failed to send
      e.printStackTrace();
    }

    return null;
  }

  /**
   * 
   * @param b The data to be encoded into Base64
   * @return The encoded version of the input as a string
   */
  private byte[] encode(byte[] b) {
    byte[] encodedOutput = Base64.getEncoder().encode(b);
    return encodedOutput;
  }

  /**
   * 
   * @param input The incoming data to be
   * @return
   */
  private byte[] decode(byte[] input) {
    byte[] decodedBytes = Base64.getDecoder().decode(input);
    return decodedBytes;
  }

  // Good practice to cleanup behind you but unsure of exactly where it will slot in yet
  public void closePipe() {
    try {
      p.closePort();
    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
