/**
 * 
 */
package common.datatypes.map.griddedmap;

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
  public Vertex(int x,int y) {
    // TODO Auto-generated constructor stub
    this.x = x;
    this.y = y;
    for (Vertex v : edges) {
        v = null;//FIXME how to check there is a datapoint adjencent?? minesweeper?
        //TODO unused?
      }
  }
  
  public void block() {
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
