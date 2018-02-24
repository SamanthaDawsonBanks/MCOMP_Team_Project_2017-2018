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
  protected Vertex[] edges = new Vertex[GriddedMap.gridDesign.getShapeSides()];// FIXME scope this
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
  protected Vertex(int x, int y) {
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
          b = GriddedMap.blocked;
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
