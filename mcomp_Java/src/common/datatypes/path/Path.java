package common.datatypes.path;

import common.datatypes.Waypoint;
import common.datatypes.linkedItem.LinkedItem;

/**
 * The Path must take a 'destination' Waypoint as part of its construction; additionally a path can
 * have an ordered (FIFO) collection of Waypoints (in LinkedItem carriers). The 'route' Waypoints
 * can be polled out (destructively) one at a time; once they are exhausted subsequent polls will
 * result in the destination always being returned until 'route' Waypoints are again added.
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.linkedItem.LinkedItem
 * @see common.datatypes.Waypoint
 * @see pathfinding.AStar
 */
public class Path {

  private LinkedItem destination;
  private LinkedItem head;
  private LinkedItem lastItem;
  private int length;

  /**
   * Constructor
   *
   * @param w The fixed destination Waypoint
   */
  public Path(Waypoint w) {
    this.destination = new LinkedItem(w);
    this.head = null;
    this.lastItem = null;
    this.length = 0;
  }

  /**
   * Additive method for attaching new route waypoints to the tail end of the collection
   * 
   * @see common.datatypes.linkedItem.LinkedItem
   * @see common.datatypes.Waypoint
   *
   * @param w The new Waypoint to add to the collection
   */
  public void addNode(Waypoint w) {
    if (length > 0) {
      lastItem.setNext(new LinkedItem(w));
      lastItem = lastItem.getNext();
    } else {
      head = new LinkedItem(w);
      lastItem = head;
    }
    length++;
  }

  /**
   * Access method for pulling (destructively) a single Waypoint out of the collection (FIFO). if
   * the collection is empty will always supply the destination Waypoint instead
   * 
   * @see common.datatypes.linkedItem.LinkedItem
   * @see common.datatypes.Waypoint
   *
   * @return The next (FIFO) Waypoint in the collection *OR* the destination if the collection is
   *         empty
   */
  public Waypoint poll() {
    // TODO test
    Waypoint res = destination.getData(); // default to destination
    if (length > 0) {
      res = head.getData();
      head = head.getNext();
      if (head == null) {
        lastItem = null;
      }
      length--;
    }
    return res;
  }


  /**
   * Access method checking the length of the Path. As a path with only a designation will be 0; can
   * be cast to boolean where True = route Waypoints, and False = destination only
   * 
   * @see common.datatypes.linkedItem.LinkedItem
   * @see common.datatypes.Waypoint
   *
   * @return length of the Path. Can be cast to boolean True = route Waypoints, False = destination
   */
  public int getLength() {
    return length;
  }


  /**
   * Assistant method for returning the *Collection* (Not the Destination) as an array. Typically
   * used for non-destructive access to the route path node during path-finding and path
   * optimisation
   * 
   * @see common.datatypes.Waypoint
   *
   * @return length of the Path. Can be cast to boolean True = route Waypoints, False = destination
   */
  public Waypoint[] toArray() {
    Waypoint[] res = new Waypoint[length];
    LinkedItem check = head;
    for (int i = 1; i <= length; i++) {
      res[i - 1] = check.getData();
      check = check.getNext();
    }
    return res;
  }

}
