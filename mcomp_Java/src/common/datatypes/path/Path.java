/**
 * 
 */
package common.datatypes.path;

import common.datatypes.Waypoint;
import common.datatypes.linkedItem.LinkedItem;

/**
 * @author David Avery 15823926
 *
 */

public class Path {

  private LinkedItem destination;
  private LinkedItem head;
  private LinkedItem lastItem;
  private int length;

  public Path(Waypoint w) {
    this.destination = new LinkedItem(w);
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
      lastItem.setNext(new LinkedItem(w));
      lastItem = lastItem.getNext();
    } else {
      head = new LinkedItem(w);
      lastItem = head;
    }
    length++;
  }

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

  public int getLength() {
    return length;
  }


  boolean isDone() {
    return (length == 0);
  }

}
