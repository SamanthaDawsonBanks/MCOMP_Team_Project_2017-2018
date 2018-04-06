/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * @author David Avery 15823926
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
    // TODO this is going to need some serious optimisation if accessed on a single point bases
    this.parent = parent;//FIXME refactor parent.parent to direct link to GMap?
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
    return chunks;//TODO depreciated?
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
