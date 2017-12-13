/**
 * 
 */
package common;

import java.util.Iterator;

/**
 * @author David Avery
 *
 */
public class MapLayer implements Iterable<Waypoint> {// FIXME adjust for iterator??

  private LiDARRead liDARRead = new LiDARread();//FIXME is this a different datatype or just a LL | AL???

  /**
   * The default constructor for a MapLayer
   */
  public MapLayer(LiDARread l) {//FIXME what is input??
    this.liDARRead = l;

  }

  public void transform(int x, int y, int a) {
    // calc trans and rot
    // x,y is a WP from the path so it is absolute and must be precalced?? before this call
    // call rot
    rotate(a);
    // call trans
    translate(x,y);
  }


  private void rotate(int a) {
    // apply rotation by azimuth (Theta) to bring map to be 'north' = 'top'
    
    // precalc sin(a) and cos(a)
    double sinA = Math.sin(a);
    double cosA = Math.cos(a);
    
    for (Waypoint w : liDARRead) {//TODO refactor for threading
      double  oldX = w.getX();
      double  oldY = w.getY();
      double newX = oldX * cosA - oldY * sinA;// FIXME fix cast
      double newY = oldX * sinA + oldY * cosA;
      w = new Waypoint(newX, newY);// FIXME does this put w back in liDARRead??
    }
  }


  private void translate(int xOffset, int yOffset) {
    // apply translation -ve x and -ve y so 'centre' is map 'origin'
    for (Waypoint w : liDARRead) {//TODO refactor for threading
      double oldX = w.getX();
      double oldY = w.getY();
      double newX = oldX - xOffset;
      double newY = oldY - yOffset;
      w = new Waypoint(newX, newY);// FIXME does this put w back in liDARRead??
    }
  }

  @Override
  public Iterator<Waypoint> iterator() {//TODO write this
    // TODO Auto-generated method stub
    return null;
  }



}
