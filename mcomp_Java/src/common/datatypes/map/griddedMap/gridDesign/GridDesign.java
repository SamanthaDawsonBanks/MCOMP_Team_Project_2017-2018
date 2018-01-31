package common.datatypes.map.griddedMap.gridDesign;

/**
 * @author David Avery
 *
 */
public class GridDesign {
//FIXME for better enum use (sides and array of relatives?)
  /**
   * Grid designs (Expand later for shapes and dimensions)
   */
  private enum GRIDDESIGN {
    /** Four sided (Regular Square) based tessellating grid */
    TETRA(4);
    private final int shapeSides;

    GRIDDESIGN(int shapeSides) {
      this.shapeSides = shapeSides;
    }

    public int getShapeSides() {
      return this.shapeSides;
    }
  }

  private GRIDDESIGN gridShape;

  public GridDesign() {
    this.gridShape = GRIDDESIGN.TETRA;
  }


  /**
   * @return the gridShape
   */
  public GRIDDESIGN getGridShape() {
    return gridShape;
  }

  /**
   * @return the gridShapeSides
   */
  public int getGridShapeSides() {
    return gridShape.getShapeSides();
  }


}