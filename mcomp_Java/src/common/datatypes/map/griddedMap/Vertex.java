/**
 * 
 */
package common.datatypes.map.griddedMap;

import java.util.ArrayList;
import java.util.Collection;
import common.datatypes.Waypoint;

/**
 * @author David Avery
 *
 */
public class Vertex {

  final static Vertex blocked = null;// FIXME should be dummy not null?
  // TODO make an "empty" vertex
  // TODO refactor to enum?

  private int x;
  private int y;
  private Vertex[] edges = new Vertex[GriddedMap.gridDesign.getShapeSides()];// FIXME scope this
                                                                             // right re-org


  /**
   * 
   */
  public Vertex(Waypoint w) {
    // TODO Auto-generated constructor stub
    this.x = (int) w.getX();
    this.y = (int) w.getY();
  }

  /**
   * 
   */
  private Vertex(int x, int y) {
    // TODO Auto-generated constructor stub
    this.x = x;
    this.y = y;
  }

  public void setBlocked() {
    // check neighbours
    for (int i = 0; i < GriddedMap.gridDesign.getShapeSides(); i++) {// change to forEach (needs
                                                                     // relative i?)
      Vertex v = edges[i];
      if (v == null) {
        v = new Vertex(this.x + GridDesign.TETRA.getNeighbourAddresses()[i].neighbourXOffset,
            this.y + GridDesign.TETRA.getNeighbourAddresses()[i].neighbourYOffset); // FIXME this
                                                                                    // needs
                                                                                    // factoring out
      }

      for (Vertex b : v.edges) {
        // set neighbours pointers, that point back to me, to be blocked
        if (b == this) {
          b = null;
        }
      }

    }
    // set self to blocked
  }


  // TODO clean this
  // public void setBlocked(Waypoint w) {
  // for (Vertex v : edges) {
  // if (v != null) { //FIXME check NPE??
  // v.cut(this);//reach into vertex at other end, disconnect 'me'
  // v = blocked;//null or data point?
  // }
  // //FIXME blocked vs does not exist?
  // }
  // for (Vertex v : edges) {
  // v = null;//FIXME how to check there is a datapoint adjacent?? minesweeper?
  // //TODO unused?
  // }
  // }
  //
  // public void cut(Vertex vIn) {
  // for (Vertex v : edges) {
  // if (v.equals(vIn)) {
  // v = blocked;
  // }
  // }
  // }

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

  /**
   * @return
   */
  public Vertex[] getAbsoluteNeighbours() {
    if (null) {
      // calc neighbour
    }
    if (blocked) {
      // calc neighbour
    }
    if (!v.equals(blocked)) {// FIXME work out what what add to prevent NPE
      res.add(v);
    }
    return (Vertex[]) res.toArray();// TODO check me
  }

  /**
   * @return
   */
  public Vertex[] getNthNeighbour(int i) {
    if (null) {
      // calc neighbour
    }
    if (blocked) {
      // calc neighbour
    }
    if (!v.equals(blocked)) {// FIXME work out what what add to prevent NPE
      res.add(v);
    }
    return (Vertex[]) res.toArray();// TODO check me
  }

  /**
   * @return the open Neighbours
   */
  public Vertex[] getOpenNeighbours() {
    Collection<Vertex> res = new ArrayList<Vertex>();
    for (Vertex v : edges) {
      if (v != null) {
        if (!v.equals(blocked)) {// FIXME work out what what add to prevent NPE
          res.add(v);
        }
      } else {

      }
    }
    return (Vertex[]) res.toArray();// TODO check me
  }

  public void add(Waypoint w) {
    // TODO Auto-generated method stub

  }

}
