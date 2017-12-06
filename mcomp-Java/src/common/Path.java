/**
 * 
 */
package common;



/**
 * @author David Avery
 *
 */

public class Path {

  private PathItem head;
  private PathItem lastItem;//TODO check
  private int length;

  public Path(Waypoint w) {
    // TODO make empty then fill or make with first
    this.head = new PathItem(w);
    this.lastItem = head;
    this.length = 1;
  }

  /**
   * Data Type definition
   */

  void addNode(Waypoint w) {
    // TODO stub
//    PathItem tempPointer = head;
//    for (int i = 0; i < length; i++) {
//      tempPointer = tempPointer.getNext();
//    }
    lastItem.setNext(new PathItem(w));
    lastItem = lastItem.getNext();
    length++;
  }

  Waypoint poll() {
    // TODO check empty??
    Waypoint res = head.getData();
    head = head.getNext();//FIXME check null
    length--;
    return res;
  }

  int getLength() {
    return length;
  }

  boolean isDone() {
    return (length == 0);
  }


}
