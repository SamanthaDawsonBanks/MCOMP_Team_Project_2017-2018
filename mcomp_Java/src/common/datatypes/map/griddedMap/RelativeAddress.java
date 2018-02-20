/**
 * 
 */
package common.datatypes.map.griddedMap;

/**
 * @author David Avery
 *
 */
class RelativeAddress {
  public final int neighbourXOffset;
  public final int neighbourYOffset;

  /**
   * 
   */
  public RelativeAddress(int x, int y) {
    // TODO Auto-generated constructor stub
    // TODO some form of check for grid type?
    neighbourXOffset = x;
    neighbourYOffset = y;
  }
}
