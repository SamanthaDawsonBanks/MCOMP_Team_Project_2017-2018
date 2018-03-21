/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;
import pathfinding.Heuristic;

/**
 * @author David Avery
 *
 */
public class Vertex {

  private int x;
  private int y;
  public Vertex[] edges;
  private Chunk parent;  public double fx;
  public double hx;
  public double gx;
  public double cost;
  public Vertex parente;
  
  public double getG()
  {
      return gx;
  }

  public void setG(double gx)
  {
      this.gx = gx;
  }

  public double getH()
  {
      return hx;
  }


  public double getF()
  {
      return fx;
  }

  public void calcF(Waypoint current, Waypoint dest)
  {
      Heuristic h = new Heuristic();
      this.hx = h.manhattanHeuristic(current, dest);
      this.fx = hx + gx;
  }
  

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
