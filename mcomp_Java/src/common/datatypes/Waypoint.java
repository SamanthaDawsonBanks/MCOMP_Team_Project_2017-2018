/**
 * 
 */
package common.datatypes;

/**
 * @author David Avery
 * 
 * 
 *
 */

public class Waypoint {
  private double x;
  private double y;

  /**
 * 
 * @param newX
 * @param newY
 */
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

  /**
   * 
   * @return
   */
  public double getY() {
    return y;
  }

  public String toString() {
    // TODO do we want this?
    return String.format("(%d,%d)", x, y);
  }

}
