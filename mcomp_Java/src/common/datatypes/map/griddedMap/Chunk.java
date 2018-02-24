package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

public class Chunk {

  private Vertex[][] vertices;
  Region parent;
  private int gridSize;

  /**
   * @param gridSize
   * @param parent
   * 
   */
  public Chunk(int gridSize, Waypoint w, Region parent) {
    // TODO Auto-generated constructor stub
    // TODO this is going to need some serious optimisation if accessed on a single point bases
    this.parent = parent;
    this.gridSize = gridSize;
    vertices = new Vertex[gridSize][gridSize];
    this.add(w);
  }


  public Vertex add(Waypoint w) {
    // TODO
    int VertexX = (int) ((w.getX() / Math.pow(gridSize, 0)) % gridSize);
    int VertexY = (int) ((w.getY() / Math.pow(gridSize, 0)) % gridSize);
    if (vertices[VertexX][VertexY] == null) {
      vertices[VertexX][VertexY] = new Vertex(w, this);
    }
    if (w.getToBeBlocked()) {
      if (!vertices[VertexX][VertexY].equals(parent.parent.blocked)) {
        vertices[VertexX][VertexY].setBlocked();
        vertices[VertexX][VertexY] = parent.parent.blocked;
      }
    }
    // calc vertex
    return vertices[VertexX][VertexY];
  }

  Vertex[][] getGrid() {// FIXME what do we want from this DS?
    return vertices;
  }


  public Vertex getVertex(Waypoint w) {
    int VertexX = (int) ((w.getX() / Math.pow(gridSize, 0)) % gridSize);
    int VertexY = (int) ((w.getY() / Math.pow(gridSize, 0)) % gridSize);
    Vertex res = null;
    if (vertices[VertexX][VertexY] != null) {
      res = vertices[VertexX][VertexY];
    }
    return res;
  }

}
