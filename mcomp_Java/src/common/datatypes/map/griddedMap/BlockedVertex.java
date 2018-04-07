/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * @author David Avery 15823926
 *
 */
public class BlockedVertex extends Vertex {
  private static BlockedVertex instance = null;

  public static BlockedVertex getInstance(GridDesign grid) {
     if(instance == null) {
        instance = new BlockedVertex(0,0, grid);
        for (int i = 0; i < instance.edges.length; i++) {
          instance.edges[i] = instance;
        }
     }
     return instance;
  }
  
  /**
   * @param x
   * @param y
   */
  private BlockedVertex(int x, int y, GridDesign grid) {
    super(new Waypoint(x, y), grid);
  }

}
