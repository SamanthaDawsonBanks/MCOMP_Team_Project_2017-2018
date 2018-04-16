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
  // assume default baud rate for Arduino board, eight data bits, two stop bits, 1 bit for
  // parity
  private final int BAUD_RATE = 115200;
  private final int NUM_OF_DATA_BITS = 8;
  private final int NUM_OF_STOP_BITS = 1;
  private final int NUM_OF_PARITY_BITS = 0;

  /**
   * Constructor for the Pipe Class. This will be responsible for opening a Serial connection on the
   * defined port and then configuring the connection parameters to those defined.
   * 
   * @param comPortName The COM port that the connection will be set up on
   */
  public Pipe(String comPortName) {
    this.comPortName = comPortName;

    try {
      p = new SerialPort(comPortName);
      p.openPort();
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
   * The basic write method for writing an individual byte
   * 
   * @param toWrite The data to write to the serial port
   * @return True if the write was successful, False if an error occurred
   */
  public boolean write(byte toWrite) {
    try {
      // write data to connected port
      return p.writeByte(toWrite);

      // wait for returned input
      // read it until '\n'
      // return total read payload

    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  /**
   * DOCME
   * 
   * @param
   * @return DOCME still needs polish so will fix before writing
   */
  public byte[] write(String toSend) {
    byte returnData[] = null;

    try {
      p.writeString(toSend);

      // wait for returned input
      while (this.available() > 0) {
        // take a read
        byte t = (p.readBytes(1))[0];
        String s = "";
      
        

        if (true/* latest thing read is '\n' */) {
          break;
        }
      }

      // return total read payload
      return returnData;

    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return new byte[0]; // return empty byte array for now
    }
  }

  /**
   * 
   * @param b The data to be encoded for sending over the serial connection
   * @return The encoded version of the input as a string
   */
  public byte[] encode(String[] params) {
    StringBuilder payloadBuilder = new StringBuilder();
    byte payload[];

    // payload
    // appending the parameters to the payload
    for (byte i = 0; i < params.length; i++) {
      payloadBuilder.append(params[i]);

      payloadBuilder.append(";"); // parameter delimiter
    }

    String builtPayload = payloadBuilder.toString();
    payload = builtPayload.getBytes();
    return payload;
  }

  /**
   * 
   * @param incomingData The incoming data from the serial to be decoded into usable data/method
   *        calls
   */
  public String[] decode(byte[] incomingData) {
    String payload = new String(incomingData); // form string of read bytes
    String delimiters = ";"; // some regex because who doesn't hate themselves - a nice
                             // optimisation though

    // break up the payload into pieces
    String[] payloadPieces = payload.split(delimiters);

    return payloadPieces;
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
