/**
 * 
 */
package common;



/**
 * @author David Avery
 *
 */

public class Path {

  private PathItem destination;
  private PathItem head;
  private PathItem lastItem;
  private int length;

  public Path(Waypoint w) {
    this.destination = new PathItem(w);
    this.head = null;
    this.lastItem = null;
    this.length = 0;
  }

  /**
   * Data Type definition
   */

  void addNode(Waypoint w) {
    // TODO test
    if (length > 0) {
      lastItem.setNext(new PathItem(w));
      lastItem = lastItem.getNext();
    } else {
      head = new PathItem(w);
      lastItem = head;
    }
    length++;
  }

  Waypoint poll() {
    // TODO test
    Waypoint res = destination.getData(); // default to destination
    if (length > 0) {
      res = head.getData();
      head = head.getNext();// FIXME LI points to old head in empty queues so GC can't clean
      if (head == null) {
        lastItem = null;// FIXME this cleans, but complicates??
      }
      length--;
    }
    return res;
  }

  int getLength() {
    return length;
  }

  boolean isDone() {
    return (length == 0);
  }


}
