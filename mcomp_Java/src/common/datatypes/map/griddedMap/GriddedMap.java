/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;
import common.datatypes.map.Map;

/**
 * @author David Avery
 *
 */
public class GriddedMap {

  protected BlockedVertex blocked;

  protected GridDesign gridDesign;// TODO abstract both up to map??
  int gridSize;
  long gridOffset;

  private Region[][] regions;


  /**
   * The default constructor for a MapLayer, with the value (TETRA | 4 | Square) for grid design
   */
  public GriddedMap(GridDesign grid, int gridSize, Map parent) {// input on constructor?
    gridDesign = grid;
    this.gridSize = gridSize;
    this.gridOffset = (long) (Math.pow(gridSize, 3)/2);
    
    regions = new Region[gridSize][gridSize];
    blocked = BlockedVertex.getInstance(grid);
  }

  /**
   * @return the gridDesign
   */
  public GridDesign getGridDesign() {
    return gridDesign;
  }

  public boolean add(Waypoint w) {
    // TODO Auto-generated method stub
    int RegionX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    int RegionY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    if (regions[RegionX][RegionY] == null) {
      regions[RegionX][RegionY] = new Region(gridSize, w, this);
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

  public Vertex getVertex(Waypoint w) {
    int RegionX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    int RegionY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    Vertex res = null;
    if (regions[RegionX][RegionY] != null) {
      res = regions[RegionX][RegionY].getVertex(w);
    }
    return res;
  }



}