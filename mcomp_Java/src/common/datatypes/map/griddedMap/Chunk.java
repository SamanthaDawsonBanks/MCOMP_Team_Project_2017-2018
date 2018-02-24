package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

public class Chunk {

  private Vertex[][] vertices = new Vertex[GriddedMap.gridSize][GriddedMap.gridSize];

  /**
   * 
   */
  public Chunk(Waypoint w) {
    // TODO Auto-generated constructor stub
    // TODO this is going to need some serious optimisation if accessed on a single point bases
    this.add(w);
  }


  public boolean add(Waypoint w) {
    // TODO
    // int VertexX = (int) ((w.getX() / (GriddedMap.gridSize ^ 0)) % GriddedMap.gridSize);
    // int VertexY = (int) ((w.getY() / (GriddedMap.gridSize ^ 0)) % GriddedMap.gridSize);
    int VertexX = (int) (w.getX() % GriddedMap.gridSize);
    int VertexY = (int) (w.getY() % GriddedMap.gridSize);
    if (vertices[VertexX][VertexY] == null) {
      vertices[VertexX][VertexY] = new Vertex(w);
    }
    if (!vertices[VertexX][VertexY].equals(GriddedMap.blocked)) {
      vertices[VertexX][VertexY].setBlocked();
      vertices[VertexX][VertexY] = GriddedMap.blocked;
    }
    // calc vertex
    return true;
  }

  Vertex[][] getGrid() {// FIXME what do we want from this DS?
    return vertices;
  }

}
