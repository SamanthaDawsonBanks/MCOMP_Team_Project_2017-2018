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
  public GriddedMap(MapLayer layer, GridDesign grid) {//input on constructor?
    this.gridDesign = grid;
    for (Waypoint w : layer) {
      this.add(w);
    }
  }

  /**
   * @return the gridDesign
   */
  public GridDesign getGridDesign() {
    return gridDesign;
  }

  public boolean add(Waypoint w) {
    return true;
    // TODO Auto-generated method stub
    
  }



}
