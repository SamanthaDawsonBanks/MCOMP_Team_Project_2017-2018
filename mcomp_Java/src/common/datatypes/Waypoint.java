/**
 * 
 */
package common.datatypes;

import common.datatypes.AngleDistance;

/**
 * @author David Avery 15823926
 * @author Stephen Pope 15836791
 *
 */

public class Waypoint {
  private double x;
  private double y;
  private boolean toBeBlocked;
  public Waypoint parent;

  /**
   * 
   * @param newX
   * @param newY
   */
  public Waypoint(double x, double y) {
    this(x, y, true);
  }

  /**
   * 
   * @param newX
   * @param newY
   */
  public Waypoint(double x, double y, boolean b) {
    this.x = x;
    this.y = y;
    this.toBeBlocked = b;
  }

  /**
   * 
   * @param AngleDistance a
   */
  public Waypoint(AngleDistance a) {
    a.getDistance();
    y = a.getDistance() * Math.sin(a.getTheta());
    x = a.getDistance() * Math.cos(a.getTheta());
  }

  /**
   * Data Type definition
   */

  public double getX() {
    return x;
  }

  /**
   * 
   * @return
   */
  public double getY() {
    return y;
  }

  /**
   * 
   * @return
   */
  AngleDistance toAngleDistance() {
    return new AngleDistance(Math.atan2(y, x) * (180 / Math.PI), Math.hypot(x, y));
  }

  /**
   * @return the toBeBlocked
   */
  public boolean getToBeBlocked() {
    return toBeBlocked;
  }

  public String toString() {
    // TODO do we want this?
    return String.format("(%.1f,%.1f)", x, y);
  }

}
