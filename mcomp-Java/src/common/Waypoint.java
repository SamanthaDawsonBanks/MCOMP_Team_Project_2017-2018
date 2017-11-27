/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */

public class Waypoint {
  private int x;
  private int y;

  public Waypoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Data Type definition
   */

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public String toString() {
    // TODO do we want this?
    return String.format("(%d,%d)", x, y);
  }

}
