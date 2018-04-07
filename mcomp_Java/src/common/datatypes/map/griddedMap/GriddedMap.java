package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;
import common.datatypes.map.Map;

/**
 * The Top Level of the Girded / Amalgamated Map data structure.
 * 
 * Encompasses: the local BlockedVertex, and the GridDesign along with the Region>Chunk>Vertices
 * 
 * @author David Avery 15823926
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see BlockedVertex
 * @see GridDesign
 * @see Region
 * 
 */
public class GriddedMap {

  public BlockedVertex blocked;

  protected GridDesign gridDesign;// TODO abstract both up to map??
  int gridSize;
  long gridOffset;

  private Region[][] regions;


  /**
   * The default constructor for a MapLayer
   * 
   * @param grid the enumerate design / shape of the storage
   * @param gridSize the number of subitem (Vertices) to store in both x and y dimensions
   * @param parent a pointer to the parent object, used for upwards calls
   * 
   */
  public GriddedMap(GridDesign grid, int gridSize, Map parent) {
    gridDesign = grid;
    this.gridSize = gridSize;
    this.gridOffset = (long) (Math.pow(gridSize, 3) / 2);

    regions = new Region[gridSize][gridSize];
    blocked = BlockedVertex.getInstance(grid);
  }

  /**
   * Returns the curent GridDesign
   * 
   * @return the locally defined GridDesign
   * 
   */
  public GridDesign getGridDesign() {
    return gridDesign;
  }

  /**
   * add the supplied Waypoint to the griddedMap as a Vertex. maintains the state of the Vertex
   * (blocked or open)
   * 
   * @see BlockedVertex
   *
   * @param w the Waypoint holder of the x/y location
   * 
   * @return boolean based on if the add was successful
   * 
   */
  public boolean add(Waypoint w) {
    int RegionX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    int RegionY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    if (regions[RegionX][RegionY] == null) {
      regions[RegionX][RegionY] = new Region(gridSize, w, this);
    } else {
      regions[RegionX][RegionY].add(w);
    }

    return true;// TODO some useful logic

  }


  public Region[][] getGrid() {
    return regions;
  }


  /**
   * Access method used read / get a Vertex from the data structure
   *
   * @param w Waypoint used to hold the x/y tuple
   * 
   * @return The Vertex at the location or null if empty
   * 
   */
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
