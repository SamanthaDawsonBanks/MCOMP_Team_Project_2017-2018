/**
 * 
 */
package common.datatypes.map.griddedmap;

import common.datatypes.Waypoint;
import common.datatypes.map.MapLayer;

/**
 * @author David Avery
 *
 */
public class GriddedMap {
  
  protected static GridDesign gridDesign;
  protected int gridSize = 64; //TODO add to constructor for dynamic?
  
  //TODO array or link set from origin???
  private Region data;
  

  /**
   * The default constructor for a MapLayer, with the value (TETRA | 4 | Square) for grid design
   */
  public GriddedMap(MapLayer layer, GridDesign grid) {//input on constructor?
    GriddedMap.gridDesign = grid;
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
