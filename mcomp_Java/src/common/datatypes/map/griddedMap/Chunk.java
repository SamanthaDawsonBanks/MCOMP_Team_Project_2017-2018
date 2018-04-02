package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * @author David Avery 15823926
 *
 */
public class Chunk {

  private Vertex[][] vertices;
  Region parent;
  private int gridSize;
  private long gridOffset;

  /**
   * @param gridSize
   * @param parent
   * 
   */
  public Chunk(int gridSize, Waypoint w, Region parent) {
    // TODO this is going to need some serious optimisation if accessed on a single point bases
    this.parent = parent;
    this.gridSize = gridSize;
    this.gridOffset = parent.gridOffset;
    vertices = new Vertex[gridSize][gridSize];
    this.add(w);
  }


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
