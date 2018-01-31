/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * @author dma23
 *
 */
public class Vertex {
  
  final static Vertex blocked = null;

  private int x;
  private int y;
  private Vertex[] edges = new Vertex[GriddedMap.gridDesign.getGridShapeSides()];//FIXME scope this right re-org
  
  
  /**
   * 
   */
  public Vertex(Waypoint w) {
    // TODO Auto-generated constructor stub
    this.x = (int) w.getX();
    this.y = (int) w.getY();
    for (Vertex v : edges) {
        v = null;//FIXME how to check there is a datapoint adjacent?? minesweeper?
        //TODO unused?
      }
  }
  
  public void add(Waypoint w) {
    for (Vertex v : edges) {
      //FIXME check NPE??
      v.cut(this);//reach into vertex at other end, disconnect 'me'
      v = blocked;//null or data point?
      //FIXME blocked vs does not exist?
    }
  }
  
  public void cut(Vertex vIn) {
    for (Vertex v : edges) {
      if (v.equals(vIn)) {
        v = blocked;
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
