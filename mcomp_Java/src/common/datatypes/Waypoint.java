/**
 * 
 */
package common.datatypes;

import common.datatypes.AngleDistance;

/**
 * Immutable Tuple for holding Cartesian x / y coordinates offset form the origin
 * 
 * @author David Avery 15823926
 * @author Stephen Pope 15836791
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.AngleDistance
 * @see common.objects.Member#lSense()
 *
 */
public class Waypoint {
  private double x;
  private double y;
  private boolean toBeBlocked;
  public Waypoint parent;

  /**
   * Base Constructor (immutable) assumes the Waypoint is a data point (to be blocked)
   * 
   * @see common.objects.Member#lSense()
   *
   * @param x The value the datapoint is offset from the origin in a 'East' (positive) 'West'
   *        (negative) axis
   * @param y The value the datapoint is offset from the origin in a 'North' (positive) 'South'
   *        (negative) axis
   * 
   */
  public Waypoint(double x, double y) {
    this(x, y, true);
  }

  /**
   * Advanced Constructor (immutable) allowing for 'open' (false) or 'blocked' (true) data points
   * 
   * @see common.objects.Member#lSense()
   *
   * @param x The value the data point is offset from the origin in a 'East' (positive) 'West'
   *        (negative) axis
   * @param y The value the data point is offset from the origin in a 'North' (positive) 'South'
   *        (negative) axis
   * @param b The value if the data point is to be 'open' (false) or 'blocked' (true)
   * 
   */
  public Waypoint(double x, double y, boolean b) {
    this.x = x;
    this.y = y;
    this.toBeBlocked = b;
  }

  /**
   * Secondary Constructor (immutable) converting from AngleDistance to Waypoint
   * 
   * @see common.datatypes.AngleDistance
   * @see common.objects.Member#lSense()
   *
   * @param a An AngleDistance Tuple object containing a rotation and distance value to be convert
   *        using trigonometry
   * 
   */
  public Waypoint(AngleDistance a) {
    a.getDistance();
    y = a.getDistance() * Math.sin(a.getTheta());
    x = a.getDistance() * Math.cos(a.getTheta());
  }

  /**
   * Access method for getting x value
   * 
   * @return X offset value in centimetres as a double
   * 
   */
  public double getX() {
    return x;
  }

  /**
   * Access method for getting x value
   * 
   * @return Y offset value in centimetres as a double
   * 
   */
  public double getY() {
    return y;
  }

  /**
   * Converter method for producing an AngleDistance from a Waypoint
   * 
   * @see common.datatypes.AngleDistance
   * 
   * @return A new AngleDsstance built from the offsets using trigonometry
   * 
   */
  AngleDistance toAngleDistance() {
    return new AngleDistance(Math.atan2(y, x) * (180 / Math.PI), Math.hypot(x, y));
  }

  /**
   * Access method for getting x value
   * 
   * @return Boolean value of whether the Waypoint is a obstacle data point 'blocked' (true) or a
   *         cordinate tuple 'open' (false)
   * 
   */
  public boolean getToBeBlocked() {
    return toBeBlocked;
  }

}
