package common.datatypes.map.griddedMap;

import java.util.ArrayList;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;

/**
 * The Top Level of the Gridded / Amalgamated Map data structure.
 * 
 * Encompasses: the local BlockedVertex, and the GridDesign along with the Region Chunk Vertices
 * 
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.map.griddedMap.BlockedVertex
 * @see common.datatypes.map.griddedMap.GridDesign
 * @see common.datatypes.map.griddedMap.Region
 * 
 */
public class GriddedMap {

  public BlockedVertex blocked;

  protected GridDesign gridDesign;
  int gridSize;
  long gridOffset;

  private Region[][] regions;


  /**
   * The default constructor for a MapLayer
   * 
   * @param grid The enumerate design / shape of the storage
   * @param gridSize The number of subitem (Vertices) to store in both x and y dimensions
   * @param root A pointer to the root object, used for upwards calls
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
   * Adds the supplied Waypoint to the griddedMap as a Vertex. Maintains the state of the Vertex
   * (blocked or open)
   * 
   * @see common.datatypes.map.griddedMap.BlockedVertex
   *
   * @param w the Waypoint holder of the x/y location
   * 
   * @return The created Vertex (so it can be linked in)
   * 
   */
  public Vertex add(Waypoint w) {
    Vertex res;
    int RegionX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    int RegionY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    if (regions[RegionX][RegionY] == null) {
      regions[RegionX][RegionY] = new Region(gridSize, w, this);
      res = getVertex(w);
    } else {
      res = regions[RegionX][RegionY].add(w);
    }
    return res;
  }


  public Vertex add(Vertex s) {
    Vertex res;
    int RegionX = (int) (((s.getX() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    int RegionY = (int) (((s.getY() + gridOffset) / Math.pow(gridSize, 2)) % gridSize);
    if (regions[RegionX][RegionY] == null) {
      regions[RegionX][RegionY] = new Region(gridSize, s, this);
      res = getVertex(new Waypoint(s.getX(), s.getY()));
    } else {
      res = regions[RegionX][RegionY].add(s);
    }
    return res;
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



  public ArrayList<Vertex> toArrayList() {
    ArrayList<Vertex> res = new ArrayList<Vertex>();

    for (int iy = 0; iy < gridSize; iy++)
      for (int ix = 0; ix < gridSize; ix++) {
        if (regions[ix][iy] == null) {
          continue;
        }

        for (int jy = 0; jy < gridSize; jy++) {
          for (int jx = 0; jx < gridSize; jx++) {
            if (regions[ix][iy].getGrid()[jx][jy] == null) {
              continue;
            }

            for (int ky = 0; ky < gridSize; ky++) {
              for (int kx = 0; kx < gridSize; kx++) {
                if (regions[ix][iy].getGrid()[jx][jy].getGrid()[kx][ky] == null) {
                  continue;
                }
                if (regions[ix][iy].getGrid()[jx][jy].getGrid()[kx][ky].equals(blocked)) {
                  int a = (int) ((ix * Math.pow(gridSize, 2)) + (jx * Math.pow(gridSize, 1)) + (jx) - gridOffset);
                  int b = (int) ((iy * Math.pow(gridSize, 2)) + (jy * Math.pow(gridSize, 1)) + (jy) - gridOffset);
                  
                  res.add(new Vertex(new Waypoint(a, b), this));
                }
              }
            }
          }
        }
      }


    
    return res;
  }



}
