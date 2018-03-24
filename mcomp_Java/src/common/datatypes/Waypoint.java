/**
 * 
 */
package common.datatypes;

/**
 * @author David Avery 15823926
 * @author Stephen Pope 15836791
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
    return new AngleDistance(Math.atan2(y,x) * (180 / Math.PI) , (long) Math.hypot(x,y)); //TODO CHECK ATAN2 RETURN VALUE
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
