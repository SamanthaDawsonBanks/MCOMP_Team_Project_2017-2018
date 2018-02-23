package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

public class Chunk {

  public Chunk() {
    // TODO Auto-generated constructor stub
  }

  private Vertex[][] data = new Vertex[GriddedMap.gridSize][GriddedMap.gridSize];

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
    int VertexX = (int) ((w.getX() / (GriddedMap.gridSize ^ 0)) % GriddedMap.gridSize);
    int VertexY = (int) ((w.getY() / (GriddedMap.gridSize ^ 0)) % GriddedMap.gridSize);
    if (data[VertexX][VertexY] == null) {
      data[VertexX][VertexY] = new Vertex(w);
    }
    if (!data[VertexX][VertexY].equals(Vertex.blocked)) {
      data[VertexX][VertexY].setBlocked();
      data[VertexX][VertexY] = Vertex.blocked;
    }
    // calc vertex
    return true;
  }

  Vertex[][] getGrid() {// FIXME what do we want from this DS?
    return data;
  }

}
