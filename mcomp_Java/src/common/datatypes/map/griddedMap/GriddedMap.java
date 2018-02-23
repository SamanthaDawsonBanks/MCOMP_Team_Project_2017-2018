/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;
import common.datatypes.map.MapLayer;

/**
 * @author David Avery
 *
 */
public class GriddedMap {

  protected static GridDesign gridDesign;// TODO abstract both up to map??
  protected static int gridSize = 64; // TODO add to constructor for dynamic?

  // TODO array or link set from origin???
  private Region[][] regions = new Region[gridSize][gridSize];


  /**
   * The default constructor for a MapLayer, with the value (TETRA | 4 | Square) for grid design
   */
  public GriddedMap(MapLayer layer, GridDesign grid) {// input on constructor?
    gridDesign = grid;
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
    // TODO Auto-generated method stub
    int RegionX = (int) ((w.getX() / (gridSize ^ 2)) % gridSize);
    int RegionY = (int) ((w.getY() / (gridSize ^ 2)) % gridSize);
    if (regions[RegionX][RegionY] == null) {
      regions[RegionX][RegionY] = new Region(w);
    } else {
      regions[RegionX][RegionY].add(w);
    }

    // calc which region
    // call add on correct region
    // rest of call in region

    return true;

  }

  public Region[][] getGrid() {
    // TODO cascade adjust for selected area??
    return regions;
  }



}
