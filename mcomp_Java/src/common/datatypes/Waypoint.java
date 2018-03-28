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
    return String.format("(%.1f,%.1f)", x, y);
  }

}
