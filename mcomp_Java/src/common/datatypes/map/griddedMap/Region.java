/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * @author David Avery
 *
 */
public class Region {

  int gridSize;
  long gridOffset;

  private Chunk[][] chunks;
  GriddedMap parent;


  /**
   * @param gridSize 
   * @param setBlocked 
   * @param parent 
   * 
   */
  public Region(int gridSize, Waypoint w, GriddedMap parent) {
    // TODO Auto-generated constructor stub
    // TODO this is going to need some serious optimisation if accessed on a single point bases
    this.parent = parent;
    this.gridSize = gridSize;
    this.gridOffset = parent.gridOffset;
    chunks = new Chunk[parent.gridSize][parent.gridSize];
    this.add(w);
  }


  public boolean add(Waypoint w) {
    // TODO
    int ChunkX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    int ChunkY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 1)) % gridSize);
    if (chunks[ChunkX][ChunkY] == null) {
      chunks[ChunkX][ChunkY] = new Chunk(gridSize, w, this);
    } else {
      chunks[ChunkX][ChunkY].add(w);
    }
    // calc which chunk
    // call add on calced chunk
    // rest of call in chunk
    return true;

  }

  Chunk[][] getGrid() {// FIXME what do we want from this DS?
    return chunks;
  }


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
