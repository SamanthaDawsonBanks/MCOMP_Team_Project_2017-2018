package member.coms;

import java.util.ArrayList;
import common.datatypes.Waypoint;
// serial communication library import
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * A communication class for handling transmission and receiving of data over a serial connection
 * between the Pi and the Arduino.
 * 
 * The write methods will be used to create structured strings which will be used to invoke method
 * on the Ardiuno i.e. the Drive method.
 * 
 * The read methods will be used to handle the data that may come back as a result of a method call
 * i.e when Sense is invoked, it will return the lidar read which will be read at this end and
 * transformed into a Map layer.
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
   * Checks that status of the serial buffer. When called, this will return the number of bytes
   * still in the serial buffer.
   * 
   * @return 0 if the serial is empty; > 0 if there is still data waiting to be processed
   * @throws SerialPortException
   */
  public int available() throws SerialPortException {
    return p.getInputBufferBytesCount();
  }

  /**
   * DOCME Standard write
   * 
   * @param toWrite
   * @return
   */
  public boolean write(byte toWrite) {
    try {
      // write data to connected port
      return p.writeByte(toWrite);

    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 
   * @param methodCall The method to be invoked on the Arduino
   * @param w The Waypoint in the path to be sent for driving
   * @return DOCME still needs polish so will fix before writing
   */
  public boolean write(String methodCall, Waypoint w) {
    String[] toEncode = new String[3];
    String x = Double.toString(w.getX()), y = Double.toString(w.getY()); // ewwww

    // build list of parameters to send to encode which will form this into a structured string for
    // TX over the serial connection
    toEncode[0] = methodCall;
    toEncode[1] = x;
    toEncode[2] = y;

    byte[] toSend = encode(toEncode);

    for (byte b : toSend) {
      write(b);
    }

    return true;
  }

  /**
   * 
   * @param methodCall
   * @return DOCME still needs polish so will fix before writing
   */
  public boolean write(String methodCall) {
    String[] toEncode = new String[1]; // consider arraylists for something even more generic - but
                                       // will it actually make an important difference

    toEncode[0] = methodCall;

    byte[] toSend = encode(toEncode);

    for (byte b : toSend) {
      write(b);
    }

    return true;
  }

  public byte[] read() throws SerialPortException { // will be changed to try/catch
    // get all data stored in the serial buffer at time of calling
    String incomingData = p.readString();

    // check it for recognised formations and handle appropriately
    this.decode(incomingData.getBytes());

    return p.readBytes();
  }

  /**
   * 
   * @param b The data to be encoded for sending over the serial connection
   * @return The encoded version of the input as a string
   */
  public byte[] encode(String[] params) {
    StringBuilder payloadBuilder = new StringBuilder();
    byte[] payload;

    // start bit
    payloadBuilder.append("SB");

    // payload
    payloadBuilder.append(":"); // method delimiter
    payloadBuilder.append(params[0]); // method to be called
    payloadBuilder.append(":"); // method delimiter

    // appending the parameters to the payload
    for (byte i = 1; i < params.length; i++) {
      payloadBuilder.append(params[i]);

      // TODO not ideal as will always put one on the end, consider using the inline if statement
      payloadBuilder.append(","); // parameter delimiter
    }

    // payload terminator
    payloadBuilder.append("\n");

    // TODO
    // form string e.g. "SB:SENSE:\n"
    // will have ^^
    // will need to turn into byte array for returning

    throw new UnsupportedOperationException("This method is not implemented yet!");
  }

  /**
   * 
   * @param input The incoming data from the serial to be decoded into usable data/method calls
   */
  // look into a sensible return type
  public ArrayList<String> decode(byte[] input) {
    // TODO
    // split up input into blocks like:
    
    //grab leading characters to categorise e.g.
    //DLREAD = data from a lidar read
    //DFN = data from arduino denoting the final waypoint reached as returned from drive
    
    // String[] data;
    // for each delimiter ',' store block as separate entry in data
    
    //return what is likely to be an array of organised data for processing with first element being type of data

    throw new UnsupportedOperationException("This method is not implemented yet!");
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
