/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * The 1st level (see GH #72) Vertex data storage structure
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.map.griddedMap.Chunk
 * @see common.datatypes.map.griddedMap.Vertex
 *
 */
public class Region {

  int gridSize;
  long gridOffset;

  private Chunk[][] chunks;
  GriddedMap root;


  /**
   * Constructor for Region
   *
   * @param gridSize the number of subitem (Vertices) to store in both x and y dimensions
   * @param w the initial Waypoint of the first data point (never created empty)
   * @param root a pointer to the root object, used for upwards calls
   * 
   */
  public Region(int gridSize, Waypoint w, GriddedMap root) {
    this.root = root;
    this.gridSize = gridSize;
    this.gridOffset = root.gridOffset;
    chunks = new Chunk[root.gridSize][root.gridSize];
    this.add(w);
  }


  public Region(int gridSize, ScoredVertex s, GriddedMap root) {
    this.root = root;
    this.gridSize = gridSize;
    this.gridOffset = root.gridOffset;
    chunks = new Chunk[root.gridSize][root.gridSize];
    this.add(s);
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
    int ChunkX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    int ChunkY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    if (chunks[ChunkX][ChunkY] == null) {
      chunks[ChunkX][ChunkY] = new Chunk(gridSize, w, root);
      res = getVertex(w);
    } else {
      res = chunks[ChunkX][ChunkY].add(w);
    }
    return res;
  }

  
  public Vertex add(ScoredVertex s) {
    Vertex res;
    int ChunkX = (int) (((s.getX() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    int ChunkY = (int) (((s.getY() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    if (chunks[ChunkX][ChunkY] == null) {
      chunks[ChunkX][ChunkY] = new Chunk(gridSize, s, root);
      res = getVertex(new Waypoint(s.getX(),s.getY()));
    } else {
      res = chunks[ChunkX][ChunkY].add(s);
    }
    return res;
  }


  
  
  Chunk[][] getGrid() {// FIXME what do we want from this DS?
    return chunks;
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
