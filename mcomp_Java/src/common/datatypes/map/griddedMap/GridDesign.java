/**
 * 
 */
package common.datatypes.map.griddedMap;

/**
 * @author David Avery 15823926
 *
 */
  // FIXME for better enum use (sides and array of relatives?)
  /**
   * Grid designs (Expand later for shapes and dimensions)
   */
  public enum GridDesign {
    /** Four sided (Regular Square) based tessellating grid */
    TETRA;

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

    public int getShapeSides() {//TODO expand from mixed grids (Octa with Tetra)
      switch (this) {
        case TETRA:
          return 4;
        default:
          throw new AssertionError("Unknown design " + this);
      }
    }
  }

