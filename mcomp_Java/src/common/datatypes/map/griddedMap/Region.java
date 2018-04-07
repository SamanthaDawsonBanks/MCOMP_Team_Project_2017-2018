/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * The 1st level (see GH #72) Vertex data storage structure
 * 
 * @author David Avery 15823926
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see Chunk
 * @see Vertex
 *
 */
public class Region {

  int gridSize;
  long gridOffset;

  private Chunk[][] chunks;
  GriddedMap parent;


  /**
   * Constructor for Region
   *
   * @param gridSize the number of subitem (Vertices) to store in both x and y dimensions
   * @param w the initial Waypoint of the first data point (never created empty)
   * @param parent a pointer to the parent object, used for upwards calls
   * 
   *        parent should be refactored to be a straight link to the griddedMap
   *
   */
  public Region(int gridSize, Waypoint w, GriddedMap parent) {
    this.parent = parent;// FIXME refactor parent.parent to direct link to GMap?
    this.gridSize = gridSize;
    this.gridOffset = parent.gridOffset;
    chunks = new Chunk[parent.gridSize][parent.gridSize];
    this.add(w);
  }


  /**
   * Adds the supplied Waypoint to the griddedMap as a Vertex. Maintains the state of the Vertex
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
    int ChunkX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    int ChunkY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    if (chunks[ChunkX][ChunkY] == null) {
      chunks[ChunkX][ChunkY] = new Chunk(gridSize, w, this);
    } else {
      chunks[ChunkX][ChunkY].add(w);
    }
    return true;// TODO some logic

  }

  Chunk[][] getGrid() {// FIXME what do we want from this DS?
    return chunks;// TODO depreciated?
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
    int ChunkX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    int ChunkY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    Vertex res = null;
    if (chunks[ChunkX][ChunkY] != null) {
      res = chunks[ChunkX][ChunkY].getVertex(w);
    }
    return res;
  }

}
