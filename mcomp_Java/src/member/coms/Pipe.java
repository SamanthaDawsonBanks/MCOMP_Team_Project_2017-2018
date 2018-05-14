package member.coms;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.datatypes.Waypoint;
import common.datatypes.map.MapLayer;
import common.objects.Member;
// serial communication library import
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * A communication class for handling transmission and receiving of data over a serial connection
 * between the Pi and the Arduino.
 * 
 * @author Ryan Shoobert 15812407
 * @author David Avery 15823926
 * 
 * @version 2.1
 * @since 2018-05-10
 * 
 */
public class Pipe {
  // global definitions
  private SerialPort p;

  String[] availablePorts;

  // Connection Parameters - Should never change these once Pipe instance created
  // assume default baud rate for Arduino board, eight data bits, two stop bits, 1 bit for
  // parity
  private final int BAUD_RATE = 115200;
  private final int NUM_OF_DATA_BITS = 8;
  private final int NUM_OF_STOP_BITS = 1;
  private final int NUM_OF_PARITY_BITS = 0;

  private static final Logger LOGGER = Logger.getLogger(Pipe.class.getName());

  /**
   * Constructor for the Pipe Class. This will be responsible for opening a Serial connection on the
   * defined port and then configuring the connection parameters to those defined.
   * 
   * @param comPortName The COM port that the connection will be set up on
   */
  public Pipe() {
    availablePorts = SerialPortList.getPortNames();

    if (availablePorts.length == 0) {
      LOGGER.log(Level.SEVERE, "There are no serial-ports available");
    }

    // Print out list of available ports
    LOGGER.log(Level.INFO, "Available Ports:");
    for (int i = 0; i < availablePorts.length; i++) {
      LOGGER.log(Level.INFO, availablePorts[i]);
    }

    p = new SerialPort(availablePorts[0]);
    LOGGER.log(Level.INFO, "Using Port: " + availablePorts[0]);

    try {
      p.openPort();
      p.setParams(BAUD_RATE, NUM_OF_DATA_BITS, NUM_OF_STOP_BITS, NUM_OF_PARITY_BITS);
    } catch (SerialPortException e) {
      e.printStackTrace();
    }
  }

  /**
   * Builds a command and sends it. This instructs the driver on the robot to drive to the x, y
   * location provided in a waypoint.
   * 
   * @param w The waypoint to drive to
   * @return The waypoint returned from the drive call. This will either be waypoint it was told to
   *         drive to or where the robot got up to before an obstacle was detected.
   */
  public Waypoint drive(Waypoint w) {
    Waypoint res = new Waypoint(0, 0);

    try {
      res = decodeWaypoint(call(encode(Commands.DRIVE, w)));
    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return res;
  }

  /**
   * As a result of a drive operation, a waypoint is returned from the driver as a string. This will
   * require separating into parts and will be used to form a new waypoint.
   * 
   * @param s The waypoint data returned from the driver
   * @return A waypoint containing the x, y coordinates of where the robot was at the end of the
   *         drive operation
   */
  private Waypoint decodeWaypoint(String s) {
    Waypoint res = new Waypoint(0, 0);
    String[] xy = s.split(";");

    res = new Waypoint(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
    return res;
  }

  /**
   * Requests a LiDAR return from the sensor, turns it into a map layer and returns it
   * 
   * @return A Map Layer representing the lidar return just collected
   */
  public MapLayer lSense() {
    LOGGER.log(Level.INFO, "begin requesting lread");

    MapLayer res = new MapLayer(null); // or new ArrayList<Waypoint>()

    try {
      res = decodeLSense(call(encode(Commands.L_SENSE, null)));
    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return res;
  }

  /**
   * Once requested, a lidar reading will be taken and returned. This method will separate each part
   * of that return into a waypoint, add it to a new map layer and return it where it can then be
   * added to the map.
   * 
   * @param s The lidar reading returned from the sense operation
   * @return A map layer representing the lidar read taken
   */
  private MapLayer decodeLSense(String s) {
    ArrayList<Waypoint> layer = new ArrayList<Waypoint>();
    String[] xys = s.split(";");

    for (int i = 0; i <= (xys.length - 2); i = i + 2) {
      layer.add(new Waypoint(Double.parseDouble(xys[i]), Double.parseDouble(xys[i + 1])));
    }

    MapLayer res = new MapLayer(layer);
    return res;
  }

  /**
   * A member needs to know the angle of direction it is facing in when it starts up so that when it
   * takes lidar reads, these can be rotated by the correct angle and amalgamated to the map.
   * 
   * @return The reading from the compass on the calling member
   */
  public double compass() {
    double res = 0.0;

    try {
      res = decodeDouble(call(encode(Commands.COMPASS, null)));
    } catch (SerialPortException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return res;
  }

  /**
   * When a compass reading is returned, it needs to be handled as a floating point number. This
   * method handles the conversion between types.
   * 
   * @param s The compass reading from the calling member
   * @return A numeric representation of the angle returned from the compass
   */
  private double decodeDouble(String s) {
    return Double.parseDouble(s);
  }

  /**
   * Once a command and it's associated data has been encoded, it will need to be sent. Once a
   * response has been sent back as a result of an operation, it needs to be read and returned for
   * decoding.
   * 
   * @param s The command/data to send over the serial connection
   * @return The return from a command call; At current will either be a waypoint, a lidar return or
   *         a compass reading
   * @throws SerialPortException If the connection is lost, then this method can complete its task
   *         successfully
   */
  private String call(String s) throws SerialPortException {

    LOGGER.log(Level.INFO, "String to send:" + s);

    p.purgePort(SerialPort.PURGE_RXCLEAR | SerialPort.PURGE_TXCLEAR);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    p.writeString(s);

    LOGGER.log(Level.INFO, "sent");

    StringBuilder sb = new StringBuilder();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    char incomingChar = (char) (p.readBytes(1))[0];

    LOGGER.log(Level.INFO, "successfully read first char");

    while (incomingChar != '\n') {
      sb.append(incomingChar);
      incomingChar = (char) (p.readBytes(1))[0];
    }

    return sb.toString();
  }

  /**
   * Before being sent, the command/data must first be formatted in a way that it can be sent, read
   * and interpreted at the other end of the connection.
   * 
   * @param c The command to perform
   * @param o Extra data to append. For the drive command, this is a waypoint, otherwise it is
   *        unused and null should be provided as the argument
   * @return The encoded command string ready for sending over the Serial connection
   */
  private String encode(Commands c, Object o) {
    StringBuilder sb = new StringBuilder();

    switch (c) {
      case COMPASS:
        LOGGER.log(Level.FINEST, "Adding COMPASS");
        sb.append("COMPASS");
        break;
      case DRIVE:
        LOGGER.log(Level.FINEST, "Adding DRIVE");
        sb.append("DRIVE");
        sb.append(';');
        sb.append(((Waypoint) o).getX());
        sb.append(';');
        sb.append(((Waypoint) o).getY());
        break;
      case L_SENSE:
        LOGGER.log(Level.FINEST, "Adding LSENSE");
        sb.append("LSENSE");
        break;
    }

    sb.append('\n');
    LOGGER.log(Level.INFO, sb.toString());

    return sb.toString();
  }

  /**
   * As part of the shutdown process, this method will close the Serial connection established
   * between the two devices.
   */
  public void closePipe() {
    try {
      p.closePort();
    } catch (SerialPortException e) {
      e.printStackTrace();
    }
  }
}
