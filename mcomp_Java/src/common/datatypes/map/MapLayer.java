/**
 * 
 */
package common.datatypes.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import common.datatypes.Waypoint;

/**
 * @author David Avery 15823926
 *
 */
public class MapLayer implements Iterable<Waypoint> {

  private ArrayList<Waypoint> liDARRead = new ArrayList<Waypoint>();

  /**
   * The default constructor for a MapLayer
   */
  public MapLayer(ArrayList<Waypoint> layer) {
    this.liDARRead = layer;

  }

  public void transform(int x, int y, int a) {
    // calc trans and rot
    // call rot
    rotate(a);
    // call trans
    translate(x, y);
  }


  private void rotate(int a) {
    // apply rotation by azimuth (Theta) to bring map to be 'north' = 'top'

    // precalc sin(a) and cos(a)
    double sinA = Math.sin(a);
    double cosA = Math.cos(a);

    double oldX;
    double oldY;
    double newX;
    double newY;

    for (Waypoint w : liDARRead) {// TODO refactor for threading
      oldX = w.getX();
      oldY = w.getY();
      newX = oldX * cosA - oldY * sinA;
      newY = oldX * sinA + oldY * cosA;
      w = new Waypoint(newX, newY);// FIXME does this put w back in liDARRead??
    }
  }


  private void translate(int xOffset, int yOffset) {
    // apply translation -ve x and -ve y so 'centre' is map 'origin'
    double oldX;
    double oldY;
    double newX;
    double newY;

    for (Waypoint w : liDARRead) {// TODO refactor for threading
      oldX = w.getX();
      oldY = w.getY();
      newX = oldX - xOffset;
      newY = oldY - yOffset;
      w = new Waypoint(newX, newY);// FIXME does this put w back in liDARRead??
    }
  }

  @Override
  public Iterator<Waypoint> iterator() {
    return new LiDARIterator();
  }

  class LiDARIterator implements Iterator<Waypoint> {
    int current = 0; // the current element we are looking at

    // return whether or not there are more elements in the array that
    // have not been iterated over.
    public boolean hasNext() {
      if (current < liDARRead.size()) {
        return true;
      } else {
        return false;
      }
    }

    // return the next element of the iteration and move the current
    // index to the element after that.
    public Waypoint next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return liDARRead.get(current++);
    }
  }

}
