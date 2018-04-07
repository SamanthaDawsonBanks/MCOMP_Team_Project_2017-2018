/**
 * 
 */
package common.datatypes.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import common.datatypes.Waypoint;

/**
<<<<<<< HEAD
 * @author David Avery
 * @author Harry Jackson 14812630
=======
 * @author David Avery 15823926
>>>>>>> branch 'develop' of https://github.com/DavidAveryUoB/Team_Project
 *
 */
public class MapLayer implements Iterable<Waypoint> {

  private ArrayList<Waypoint> liDARRead = new ArrayList<Waypoint>();
  private Waypoint trackedCentre = new Waypoint(0, 0);
  private int trackedRotation = 0;
  private int trackedScale = 1;
  private boolean trackedOpens = false;

  /**
   * The default constructor for a MapLayer
   */
  public MapLayer(ArrayList<Waypoint> layer) {
    this.liDARRead = layer;

  }

  /**
   * The private constructor for a MapLayer that includes passing of centre, rotation, and scale
   * @param opensAdded TODO
   */
  private MapLayer(ArrayList<Waypoint> layer, int angle, double xOffset, double yOffset, int scale, boolean opensAdded) {
    this.liDARRead = layer;
    this.trackedCentre = new Waypoint(xOffset,yOffset);
    this.trackedRotation = angle;
    this.trackedScale  = scale;
    this.trackedOpens = opensAdded;

  }

  public MapLayer transform(int angle, int xOffset, int yOffset, int scale) {
    return rotate(angle).translate(xOffset, yOffset).scale(scale);
  }


  private MapLayer rotate(int a) {
    // apply rotation by azimuth (Theta) to bring map to be 'north' = 'top'

    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    double sinA = Math.sin(Math.toRadians(a));
    double cosA = Math.cos(Math.toRadians(a));

    double oldX;
    double oldY;
    double newX;
    double newY;

    trackedRotation = trackedRotation + a;

    for (Waypoint w : liDARRead) {
      oldX = w.getX();
      oldY = w.getY();
      newX = oldX * cosA - oldY * sinA;
      newY = oldX * sinA + oldY * cosA;
      res.add(new Waypoint(newX, newY));
    }
    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(), trackedScale, trackedOpens);
  }


  private MapLayer translate(int xOffset, int yOffset) {
    // apply translation -ve x and -ve y so 'centre' is map 'origin'

    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    double oldX;
    double oldY;
    double newX;
    double newY;

    trackedCentre = new Waypoint(trackedCentre.getX() + xOffset, trackedCentre.getY() + yOffset);

    for (Waypoint w : liDARRead) {
      oldX = w.getX();
      oldY = w.getY();
      newX = oldX - xOffset;
      newY = oldY - yOffset;
      res.add(new Waypoint(newX, newY));
    }
    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(), trackedScale, trackedOpens);
  }

  /**
   * Method takes in array and scales the Waypoints
   * 
   */
  public MapLayer scale(int scale) {
    ArrayList<Waypoint> res = new ArrayList<Waypoint>();
    
    trackedScale = trackedScale + scale;
    
    for (Waypoint w : liDARRead) {
      res.add(new Waypoint(w.getX() * scale, w.getY() * scale));
    }
    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(), trackedScale, trackedOpens);
  }


  public MapLayer addOpens() {
    // add WPs for 'open' reads between sensor and Blocked

    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    double xOffset = this.trackedCentre.getX();
    double yOffset = this.trackedCentre.getY();

    trackedOpens = true;
    
    double i;
    double step = 1.0;

    // for each WP in read
    for (Waypoint w : liDARRead) {
      double thisX = w.getX();
      double thisY = w.getY();
      // check offset
      // calc hypot
      i = Math.hypot(w.getX() - xOffset, w.getY() - yOffset) / step;// TODO decide on scale (1,
                                                                    // 0.1??)
      // for 0>i
      for (double j = 1; j < i; j = j + step) {// TODO refactor for lass vars //TODO if scale is
                                               // adjusted then so is step
        // FIXME adjust for negative!!
        double scaledX = (thisX - xOffset) * (j / i);
        double scaledY = (thisY - yOffset) * (j / i);

        // add 'open' WP along track
        res.add(new Waypoint((scaledX + xOffset), (scaledY + yOffset), false));
      }
      // add blocked WP at end of track
      res.add(w);
    }

    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(), trackedScale, trackedOpens);
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

  
  public ArrayList<Waypoint> getWaypoints() {
    // TODO Auto-generated method stub
    return liDARRead;
  }

}
