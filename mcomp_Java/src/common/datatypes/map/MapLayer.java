/**
 * 
 */
package common.datatypes.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import common.datatypes.Waypoint;

/**
 * @author David Avery
 * @author Harry Jackson 14812630
 *
 */
public class MapLayer implements Iterable<Waypoint> {

  private ArrayList<Waypoint> liDARRead = new ArrayList<Waypoint>();
  private Waypoint relativeCentre = new Waypoint(0, 0);

  /**
   * The default constructor for a MapLayer
   */
  public MapLayer(ArrayList<Waypoint> layer) {
    this.liDARRead = layer;

  }

  public MapLayer transform(int xOffset, int yOffset, int angle, int scale) {
    return rotate(angle).translate(xOffset, yOffset).scale(scale);
  }


  private MapLayer rotate(int a) {
    // apply rotation by azimuth (Theta) to bring map to be 'north' = 'top'

    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    // precalc sin(a) and cos(a)
    double sinA = Math.sin(Math.toRadians(a));
    double cosA = Math.cos(Math.toRadians(a));

    double oldX;
    double oldY;
    double newX;
    double newY;

    for (Waypoint w : liDARRead) {
      oldX = w.getX();
      oldY = w.getY();
      newX = oldX * cosA - oldY * sinA;
      newY = oldX * sinA + oldY * cosA;
      res.add(new Waypoint(newX, newY));
    }
    return new MapLayer(res);
  }


  private MapLayer translate(int xOffset, int yOffset) {
    // apply translation -ve x and -ve y so 'centre' is map 'origin'

    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    double oldX;
    double oldY;
    double newX;
    double newY;

    relativeCentre = new Waypoint(xOffset, yOffset);

    for (Waypoint w : liDARRead) {
      oldX = w.getX();
      oldY = w.getY();
      newX = oldX - xOffset;
      newY = oldY - yOffset;
      res.add(new Waypoint(newX, newY));
    }
    return new MapLayer(res);
  }
  
  /**
   * Method takes in array and scales the Waypoints
   * 
   */
  public MapLayer scale(int scale){
      ArrayList<Waypoint> res = new ArrayList<Waypoint>();
      for(Waypoint w: liDARRead) {
          res.add(new Waypoint(w.getX()*scale, w.getY()*scale));
      }
      return new MapLayer(res);
  }
  

  public MapLayer addOpens() {
    // add WPs for 'open' reads between sensor and Blocked

    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    double xOffset = this.relativeCentre.getX();
    double yOffset = this.relativeCentre.getY();
    
    double i;
    double step = 1.0;
    
    // for each WP in read
    for (Waypoint w : liDARRead) {
      double thisX = w.getX();
      double thisY = w.getX();
      // check offset
      // calc hypot
      i = Math.hypot(w.getX()-xOffset, w.getY()-yOffset) / step;//TODO decide on scale (1, 0.1??)
      // for 0>i
      for (double j = 0; j<=i; j = j + step) {//TODO refactor for lass vars //TODO if scale is adjusted then so is step
        double scaledX = (thisX-xOffset) * j;
        double scaledY = (thisY-yOffset) * j;
        
        // add 'open' WP along track
        res.add(new Waypoint((scaledX+xOffset),(scaledY+yOffset),false));
      }
      // add blocked WP at end of track
      res.add(w);
    }

    return new MapLayer(res);
  }



  @Override
  public Iterator<Waypoint> iterator() {// TODO write this
    // TODO Auto-generated method stub

    return new LiDARIterator();

    // return null;
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

  public ArrayList<Waypoint> getWaypoints() {
    // TODO Auto-generated method stub
    return liDARRead;
  }

}
