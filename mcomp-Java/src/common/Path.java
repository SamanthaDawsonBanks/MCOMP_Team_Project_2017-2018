/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */

public class Path {

  private Waypoint head;
  private int length;

  public Path(Waypoint w) {
    // TODO make empty then fill or make with first
    this.head = w; // reuse PQueue???
    length = 1;
  }

  /**
   * Data Type definition
   */

  void addNode(Waypoint w) {
    // TODO stub
    length++;// want this yes/no??
  }

  Waypoint getNext() {// getNext or pop? or get [i]
    // TODO stub
    return head;
  }

  int getLength() {
    // TODO stub
    // do we want this??
    return length;
  }

  boolean isDone() {
    // TODO stub
    return true;// check length??
  }

}
