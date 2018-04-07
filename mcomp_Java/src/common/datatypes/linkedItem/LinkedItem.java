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
public class LinkedItem {

  private Waypoint data;
  private LinkedItem next;

  /**
   * Constructor
   *
   * @param The Waypoint to be held
   */
  public LinkedItem(Waypoint data) {
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
   * Returns (a pointer to) the next LinkedItem in the chain
   * 
   * @return Pointer to the nest LinkedItem. or null if end of chain
   * 
   */
  public LinkedItem getNext() {
    return next;
  }

  /**
   * Points the pointer to the provided LinkedItem
   * 
   * @param NextPathItem in the collection
   * 
   */
  public void setNext(LinkedItem next) {
    this.next = next;
  }

}

