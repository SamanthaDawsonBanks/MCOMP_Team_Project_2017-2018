package common.datatypes.map.griddedMap;

/**
 * Enum fo Grid designs (Expand later for shapes and dimensions)
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see GridedMap
 *
 */
public enum GridDesign {
  /** Four sided (Regular Square) based tessellating grid */
  TETRA;


  /**
   * Access method for the grid design neighbour addresses
   * 
   * @see Vertex
   *
   * @return Array (size based on design) holding the relative addresses of the neighbours
   * 
   */
  RelativeAddress[] getNeighbourAddresses() {
    switch (this) {
      case TETRA:
        RelativeAddress[] res = {new RelativeAddress(0, 1), new RelativeAddress(1, 0),
            new RelativeAddress(0, -1), new RelativeAddress(-1, 0)};
        return res;
      default:
        throw new AssertionError("Unknown design " + this);
    }
  };

  /**
   * Access method for the grid design neighbours
   * 
   * @see Vertex
   *
   * @return Number of edges that each Vertex of this type has
   * 
   */
  public int getShapeSides() {// TODO expand from mixed grids (Octa with Tetra) array of sides?
    switch (this) {
      case TETRA:
        return 4;
      default:
        throw new AssertionError("Unknown design " + this);
    }
  }
}

