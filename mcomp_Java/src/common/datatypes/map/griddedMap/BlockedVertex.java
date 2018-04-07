package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * Static representation of a blocked node in the vertex map
 * 
 * Implements the Singleton Pattern to allow for easier equality checking
 * 
 * @author David Avery 15823926
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see Vertex
 *
 */
public class BlockedVertex extends Vertex {
  private static BlockedVertex instance = null;

  /**
   * Public access method for receiving or creating the Singleton
   * 
   * @param grid The Grid Design (to allocate vertex edges)
   * @return The Singleton instance
   * 
   */
  public static BlockedVertex getInstance(GridDesign grid) {
    if (instance == null) {
      instance = new BlockedVertex(0, 0, grid);
      for (int i = 0; i < instance.edges.length; i++) {
        instance.edges[i] = instance;
      }
    }
    return instance;
  }

  /**
   * Private Singleton Constructor
   * 
   * @param x the x 'location' of the blocked vertex
   * @param y the y 'location' of the blocked vertex
   * 
   * @return new (single) blocked vertex
   * 
   */
  private BlockedVertex(int x, int y, GridDesign grid) {
    super(new Waypoint(x, y), grid);
  }

}
