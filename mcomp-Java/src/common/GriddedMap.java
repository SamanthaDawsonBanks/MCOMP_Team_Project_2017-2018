/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public class GriddedMap {
  
  private GridDesign gridDesign;

  /**
   * The default constructor for a MapLayer, with the value (TETRA | 4 | Square) for grid design
   */
  public GriddedMap(LiDARRead l) {//input on constructor?
    this.gridDesign = new GridDesign();
  }

  /**
   * @return the gridDesign
   */
  public GridDesign getGridDesign() {
    return gridDesign;
  }



}
