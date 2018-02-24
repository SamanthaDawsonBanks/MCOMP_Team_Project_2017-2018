/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * @author David Avery
 *
 */
public class Vertex {

  private int x;
  private int y;
  protected Vertex[] edges;
  private Chunk parent;

  /**
   * @param parent
   * 
   */
  public Vertex(Waypoint w, Chunk parent) {
    // TODO Auto-generated constructor stub
    this.x = (int) w.getX();
    this.y = (int) w.getY();
    this.parent = parent;
    edges = new Vertex[parent.parent.parent.gridDesign.getShapeSides()];// TODO not sure this is
                                                                        // better?
    connectNeighbours();
  }

  protected Vertex(Waypoint w, GridDesign grid) {// only for blocked
    // TODO Auto-generated constructor stub
    this.x = (int) w.getX();
    this.y = (int) w.getY();
    edges = new Vertex[grid.getShapeSides()];
  }

  private void connectNeighbours() {
    // TODO Auto-generated method stub
    // check neighbours
    for (int i = 0; i < edges.length; i++) {
      int xOffset = GridDesign.TETRA.getNeighbourAddresses()[i].neighbourXOffset;
      int yOffset = GridDesign.TETRA.getNeighbourAddresses()[i].neighbourYOffset;
      Waypoint neighbourAddress = new Waypoint(this.x + xOffset,this.y + yOffset, false);
      edges[i] = parent.parent.parent.getVertex(neighbourAddress);
      if (edges[i] != null) {//FIXME and not blocked?
        edges[i].edges[((i+(edges.length/2))%edges.length)] = this;
      }

    }
  }

  public void setBlocked() {
    // check neighbours
    for (int i = 0; i < parent.parent.parent.gridDesign.getShapeSides(); i++) {// change to forEach
                                                                               // (needs
      // relative i?)
      Vertex v = edges[i];
      if (v == null) {
        v = parent
            .add(new Waypoint(this.x + GridDesign.TETRA.getNeighbourAddresses()[i].neighbourXOffset,
                this.y + GridDesign.TETRA.getNeighbourAddresses()[i].neighbourYOffset, false)); // FIXME
                                                                                                // this
        // needs
        // factoring out
      }

      for (Vertex b : v.edges) {
        // set neighbours pointers, that point back to me, to be blocked
        if (b == this) {
          b = parent.parent.parent.blocked;
        }
      }

    }
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @return the y
   */
  public int getY() {
    return y;
  }



}
