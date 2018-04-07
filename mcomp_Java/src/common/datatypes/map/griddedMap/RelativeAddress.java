/**
 * 
 */
package common.datatypes.map.griddedMap;

/**
 * @author David Avery 15823926
 *
 */
class RelativeAddress {
  public final int neighbourXOffset;
  public final int neighbourYOffset;

  /**
   * 
   */
  public RelativeAddress(int x, int y) {
    neighbourXOffset = x;
    neighbourYOffset = y;
  }
}
