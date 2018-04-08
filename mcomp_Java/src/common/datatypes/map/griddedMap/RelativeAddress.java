package common.datatypes.map.griddedMap;

/**
 * Tuple holding the offset (x/y) for each address Carier Object
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.map.griddedMap.GridDesign
 * @see common.datatypes.map.griddedMap.Vertex
 *
 */
class RelativeAddress {
  public final double neighbourXOffset;
  public final double neighbourYOffset;

  /**
   * Constructor
   * 
   */
  public RelativeAddress(double x, double y) {
    neighbourXOffset = x;
    neighbourYOffset = y;
  }
}
