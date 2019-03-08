package common.datatypes.map.griddedMap;

import java.io.Serializable;
import common.datatypes.Waypoint;

/**
 * The 2nd level (see GH #72) Vertex data storage structure
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.map.griddedMap.Region
 * @see common.datatypes.map.griddedMap.Vertex
 *
 */
public class Chunk implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -1721206550805261431L;
  private Vertex[][] vertices;
  GriddedMap root;
  private int gridSize;
  private long gridOffset;

  /**
   * Constructor for Chunk
   *
   * @param gridSize the number of subitem (Vertices) to store in both x and y dimensions
   * @param w the initial Waypoint of the first data point (never created empty)
    * @param root a pointer to the root object, used for upwards calls
  * 
   */
  public Chunk(int gridSize, Waypoint w, GriddedMap root) {
    this.root = root;
    this.gridSize = gridSize;
    this.gridOffset = root.gridOffset;
    vertices = new Vertex[gridSize][gridSize];
    this.add(w);
  }


  public Chunk(int gridSize, Vertex s, GriddedMap root) {
    this.root = root;
    this.gridSize = gridSize;
    this.gridOffset = root.gridOffset;
    vertices = new Vertex[gridSize][gridSize];
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
    int VertexX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    int VertexY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    if (vertices[VertexX][VertexY] == null) {
      vertices[VertexX][VertexY] = new Vertex(w, root);
    }
    if (w.getToBeBlocked()) {
      if (!vertices[VertexX][VertexY].equals(root.blocked)) {
        vertices[VertexX][VertexY].setBlocked();
        vertices[VertexX][VertexY] = root.blocked;
      }
    }
    return vertices[VertexX][VertexY];
  }


  public Vertex add(Vertex s) {
    int VertexX = (int) (((s.getX() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    int VertexY = (int) (((s.getY() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    if (vertices[VertexX][VertexY] == null) {
      vertices[VertexX][VertexY] = s;
    }
    return vertices[VertexX][VertexY];
  }



  Vertex[][] getGrid() {// FIXME what do we want from this DS?
    return vertices;
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
    int VertexX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    int VertexY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    Vertex res = null;
    if (vertices[VertexX][VertexY] != null) {
      res = vertices[VertexX][VertexY];
    }
    return res;
  }

}
