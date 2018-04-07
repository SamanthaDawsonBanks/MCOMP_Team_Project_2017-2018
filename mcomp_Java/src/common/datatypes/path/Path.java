/**
 * 
 */
package common.datatypes.path;

import common.datatypes.Waypoint;
import common.datatypes.linkedItem.PathItem;

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

  public void addNode(Waypoint w) {
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

  public Waypoint poll() {
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


  public int getLength() {
    return length;
  }


  public boolean isDone() {
    return (length == 0);
  }


  public Waypoint[] toArray() {
    Waypoint[] res = new Waypoint[length];//may return an array of size 0
    PathItem check = head;
    for (int i = 1; i <= length; i++) {
      res[i-1] = check.getData();
      check = check.getNext();
    }
    return res;
  }

}
