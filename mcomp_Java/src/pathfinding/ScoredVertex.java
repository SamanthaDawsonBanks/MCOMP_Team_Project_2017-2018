package pathfinding;

import common.datatypes.Waypoint;
import common.datatypes.map.griddedMap.GriddedMap;
import common.datatypes.map.griddedMap.Vertex;

/**
 * Specialisation of the vertex class that also carries scoring for AStar search
 * 
 * @author David Avery 15823926
 * @author Harry Jackson 14812630
 *
 * @version 1.0
 * @since 2018-04-11
 * 
 * @see common.datatypes.map.griddedMap.Vertex
 * @see pathfinding.AStar
 */
public class ScoredVertex extends Vertex {

//  private double fx;
  private double hx;
  private double gx;
  private double cost;
  public Vertex parent;
  private String direction;

  /**
   * @param v The Vertex to specialise into a ScoredVertex
   * @param root a pointer to the root object, used for upwards calls
   */
  public ScoredVertex(Vertex v, GriddedMap m) {
    super(new Waypoint(v.getX(), v.getY()), m);
    // TODO Auto-generated constructor stub
  }

  /**
   * @return the fx
   */
  public double getFx() {
    return hx + gx;
    //return fx;
  }

//  /**
//   * @param fx the fx to set
//   */
//  public void setFx(double fx) {
//    this.fx = fx;
//  }

  /**
   * @return the hx
   */
  public double getHx() {
    return hx;
  }

  /**
   * @param hx the hx to set
   */
  public void setHx(double hx) {
    this.hx = hx;
  }

  /**
   * @return the gx
   */
  public double getGx() {
    return gx;
  }

  /**
   * @param gx the gx to set
   */
  public void setGx(double gx) {
    this.gx = gx;
  }

  /**
   * @return the cost
   */
  public double getCost() {
    return cost;
  }

  /**
   * Method for calculating the direction a vertex is facing
   * by comparing the current vertex with another (child/neighbour).
   *  
   * @param Vertex current, the current vertex.
   * @param Vertex child, the adjacent Vertices to the current Vertex.
   */
  public void setDirection(Vertex current, Vertex child) {
    if(child.getX() - current.getX() > 0) {
      this.direction = "east";
    }
    if(child.getX() - current.getX() < 0) {
      this.direction = "west";
    }
    if(child.getY() - current.getY() < 0) {
      this.direction = "south";
    }
    if(child.getY() - current.getY() > 0) {
      this.direction = "north";
    }
  }

  /**
   * @return the direction associated with moving in a direction
   */
  public String getDirection() {
    return direction;
  }

  /**
   * @param cost the cost to set
   */
  public void setCost(double cost) {
    this.cost = cost;
  }



}
