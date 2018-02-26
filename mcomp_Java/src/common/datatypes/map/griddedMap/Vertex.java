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
      int xOffset = parent.parent.parent.gridDesign.getNeighbourAddresses()[i].neighbourXOffset;
      int yOffset = parent.parent.parent.gridDesign.getNeighbourAddresses()[i].neighbourYOffset;
      Waypoint neighbourAddress = new Waypoint(this.x + xOffset,this.y + yOffset, false);
      edges[i] = parent.parent.parent.getVertex(neighbourAddress);
      if (edges[i] != null) {//FIXME and not blocked?
        edges[i].edges[((i+(edges.length/2))%edges.length)] = this;
      }

    }
  }

  public void setBlocked() {
    // check neighbours
    for (int i = 0; i < parent.parent.parent.gridDesign.getShapeSides(); i++) {
      if (edges[i] == null) {
        edges[i] = parent
            .add(new Waypoint(this.x + parent.parent.parent.gridDesign.getNeighbourAddresses()[i].neighbourXOffset,
                this.y + parent.parent.parent.gridDesign.getNeighbourAddresses()[i].neighbourYOffset, false)); // FIXME
                                                                                                // this
        // needs
        // factoring out
      }

      for (int j = 0; j < parent.parent.parent.gridDesign.getShapeSides(); j++) {
        // set neighbours pointers, that point back to me, to be blocked
        if (edges[i].edges[j] == this) {
          edges[i].edges[j] = parent.parent.parent.blocked;
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
