package common.datatypes.linkedItem;

import common.datatypes.Waypoint;

/**
 * A simple data holder for Waypoint type items.
 * 
 * A later refinement would be to refactor to objects of type T
 * 
 * @author David Avery 15823926
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see Waypoint
 */
public class PathItem {

  private Waypoint data;
  private PathItem next;

  /**
   * Constructor
   *
   * @param The Waypoint to be held
   */
  public PathItem(Waypoint data) {
    this.data = data;
    this.next = null;
  }

  /**
   * Returns the data (Waypoint Object) held
   * 
   * @see Waypoint
   *
   * @return Waypoint from collection
   */
  public Waypoint getData() {
    return data;
  }

  /**
   * Returns (a pointer to) the next PathItem in the chain
   * 
   * @return Pointer to the nest PathItem. or null if end of chain
   * 
   */
  public PathItem getNext() {
    return next;
  }

  /**
   * Sets the point to point to the provided PathItem
   * 
   * @param NextPathItem in the collection
   * 
   */
  public void setNext(PathItem next) {
    this.next = next;
  }

}

