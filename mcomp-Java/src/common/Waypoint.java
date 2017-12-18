/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */

public class Waypoint {
  private double x;
  private double y;

  public Waypoint(double newX, double newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * Data Type definition
   */

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public String toString() {
    // TODO do we want this?
    return String.format("(%d,%d)", x, y);
  }

}
