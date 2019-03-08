package common.datatypes.map.griddedMap;

import java.io.Serializable;
import common.datatypes.Waypoint;

/**
 * The Base (nodal) component of the Map, Graph (as per Graph Theory). Each Vertex will have a
 * number of Edges that connect it to other Vertices.
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.map.griddedMap.GriddedMap
 * @see common.datatypes.map.MapLayer
 * @see common.datatypes.Waypoint
 * @see common.datatypes.map.griddedMap.Vertex
 * @see common.datatypes.map.griddedMap.BlockedVertex
 */
public class Vertex implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7688895541614215077L;
  private long x;
  private long y;
  public Vertex[] edges;
  
  private GriddedMap root;

  /**
   * The main constructor for a Vertex that includes the Waypoint for location and Parent for
   * upwards calls
   * 
   * @see common.datatypes.Waypoint
   * @see common.datatypes.map.griddedMap.Chunk
   * 
   * @param w The Waypoint for setting (rounded / localised) location
   * @param root a pointer to the root object, used for upwards calls
   */
  public Vertex(Waypoint w, GriddedMap root) {
    this.x = (int) w.getX();
    this.y = (int) w.getY();
    this.root = root;
    edges = new Vertex[root.gridDesign.getShapeSides()];
    connectNeighbours();
  }

  /**
   * The secondary constructor only used to create the BlockedVertex Specialised object
   * 
   * @see common.datatypes.Waypoint
   * @see common.datatypes.map.griddedMap.GridDesign
   * 
   * @param w The Waypoint for setting location (BlockedVertex is at 0,0)
   * @param grid the GridDesign for setting the size of the edges array
   */
  protected Vertex(Waypoint w, GridDesign grid) {
    this.x = (int) w.getX();
    this.y = (int) w.getY();
    edges = new Vertex[grid.getShapeSides()];
  }

  /**
   * Action method for connecting the outgoing and incoming edge connectors are linked, creating
   * them if necessary
   * 
   * @see common.datatypes.map.griddedMap.GriddedMap
   * @see common.datatypes.map.MapLayer
   * @see common.datatypes.map.griddedMap.BlockedVertex
   */
  private void connectNeighbours() {
    for (int i = 0; i < edges.length; i++) {
      double xOffset = root.gridDesign.getNeighbourAddresses()[i].neighbourXOffset;
      double yOffset = root.gridDesign.getNeighbourAddresses()[i].neighbourYOffset;
      Waypoint neighbourAddress = new Waypoint(this.x + xOffset, this.y + yOffset, false);
      edges[i] = root.getVertex(neighbourAddress);
      if (edges[i] != null) {// FIXME and not blocked?
        edges[i].edges[((i + (edges.length / 2)) % edges.length)] = this;
      }

    }
  }

  /**
   * Action method for setting a Vertex to be blocked, whilst also ensure that the incomming edge
   * connectors are linked to 'blocked', creating them if necessary
   * 
   * @see common.datatypes.map.griddedMap.GriddedMap
   * @see common.datatypes.map.MapLayer
   * @see common.datatypes.map.griddedMap.BlockedVertex
   */
  protected void setBlocked() {
    for (int i = 0; i < root.gridDesign.getShapeSides(); i++) {
      if (edges[i] == null) {
        edges[i] = root
            .add(new Waypoint(this.x + root.gridDesign.getNeighbourAddresses()[i].neighbourXOffset,
                this.y + root.gridDesign.getNeighbourAddresses()[i].neighbourYOffset, false));
      }

      for (int j = 0; j < root.gridDesign.getShapeSides(); j++) {
        if (edges[i].edges[j] == this) {
          edges[i].edges[j] = root.blocked;
        }
      }

    }
  }

  /**
   * Access method for getting the list of abilities from the member instance
   * 
   * @see common.datatypes.Waypoint
   * 
   * @return The X Cartesian location of the vertex
   */
  public long getX() {
    return x;
  }

  /**
   * Access method for getting the list of abilities from the member instance
   * 
   * @see common.datatypes.Waypoint
   * 
   * @return The Y Cartesian location of the vertex
   */
  public long getY() {
    return y;
  }



}
