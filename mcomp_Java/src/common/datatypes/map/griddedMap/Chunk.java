package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * The 2nd level (see GH #72) Vertex data storage structure
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see Region
 * @see Vertex
 *
 */
public class Chunk {

  private Vertex[][] vertices;
  Region parent;
  private int gridSize;
  private long gridOffset;

  /**
   * Constructor for Chunk
   *
   * @param gridSize the number of subitem (Vertices) to store in both x and y dimensions
   * @param w the initial Waypoint of the first data point (never created empty)
   * @param parent a pointer to the parent object, used for upwards calls
   * 
   *        parent should be refactored to be a straight link to the griddedMap
   *
   */
  public Chunk(int gridSize, Waypoint w, Region parent) {
    this.parent = parent;
    this.gridSize = gridSize;
    this.gridOffset = parent.gridOffset;
    vertices = new Vertex[gridSize][gridSize];
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
   * @return The created Vertex (so it can be linked in)
   * 
   */
  public Vertex add(Waypoint w) {
    int VertexX = (int) (((w.getX() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    int VertexY = (int) (((w.getY() + gridOffset) / Math.pow(gridSize, 0)) % gridSize);
    if (vertices[VertexX][VertexY] == null) {
      vertices[VertexX][VertexY] = new Vertex(w, this);
    }
    if (w.getToBeBlocked()) {
      if (!vertices[VertexX][VertexY].equals(parent.parent.blocked)) {
        vertices[VertexX][VertexY].setBlocked();
        vertices[VertexX][VertexY] = parent.parent.blocked;
      }
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
