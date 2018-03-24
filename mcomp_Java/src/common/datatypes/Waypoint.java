/**
 * 
 */
package common.datatypes;

import common.datatypes.AngleDistance;

/**
 * @author David Avery
 * 
 * 
 *
 */

public class Waypoint {
  private double x;
  private double y;
  private boolean toBeBlocked;

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
    // TODO Auto-generated constructor stub
    a.getDistance();
    y = a.getDistance() * sin(a.getTheta());
    x = a.getDistance() * cos(a.getTheta());
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
   * @return the toBeBlocked
   */
  public boolean getToBeBlocked() {
    return toBeBlocked;
  }

  public String toString() {
    // TODO do we want this?
    return String.format("(%d,%d)", x, y);
  }

}
