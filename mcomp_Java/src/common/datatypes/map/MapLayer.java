package common.datatypes.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import common.datatypes.Waypoint;

/**
 * Data structure for storing and manipulating (in an immutable fashion) a collection of Waypoints
 * 
 * @author David Avery
 * @author Harry Jackson 14812630
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.Waypoint
 * @see common.objects.Member#lSense()
 *
 */
public class MapLayer implements Iterable<Waypoint> {

  private ArrayList<Waypoint> liDARRead = new ArrayList<Waypoint>();
  private Waypoint trackedCentre = new Waypoint(0, 0);
  private double trackedRotation = 0;
  private double trackedScale = 1;
  private boolean trackedOpens = false;

  /**
   * The main constructor for a MapLayer
   * 
   * @see common.datatypes.Waypoint
   * @see common.objects.Member#lSense()
   *
   * @param layer the collection of Waypoints forming the LiDAR return
   */
  public MapLayer(ArrayList<Waypoint> layer) {
    this.liDARRead = layer;

  }

  /**
   * The private constructor for a MapLayer that includes passing of centre, rotation, scale, and
   * whether the 'open indicators' have been added
   * 
   * @see common.datatypes.Waypoint
   * @see common.objects.Member#lSense()
   * 
   * @param layer
   * @param angle
   * @param xOffset
   * @param yOffset
   * @param scale
   * @param opensAdded
   */
  private MapLayer(ArrayList<Waypoint> layer, double angle, double xOffset, double yOffset, double scale,
      boolean opensAdded) {
    this.liDARRead = layer;
    this.trackedCentre = new Waypoint(xOffset, yOffset);
    this.trackedRotation = angle;
    this.trackedScale = scale;
    this.trackedOpens = opensAdded;

  }

  /**
   * function for manipulating (combined rotate, translate, and scale) the immutable MapLayer
   * 
   * @see common.datatypes.Waypoint
   * @see common.objects.Member#lSense()
   *
   * @param angle The angle (in degrees) to rotate the Waypoints in a clockwise (positive)
   *        counterclockwise (negative) aspect
   * @param xOffset The value to translate (move) the Waypoints in a 'East' (positive) 'West'
   *        (negative) axis
   * @param yOffset The value to translate (move) the Waypoints in a 'North' (positive) 'South'
   *        (negative) axis
   * @param scale The value to scale the Waypoints in a 'larger' (positive) 'smaller' (negative)
   *        aspect relative to 1.0
   * 
   * @return A new MapLayer with the applied compound transformations
   */
  public MapLayer transform(double angle, double xOffset, double yOffset, double scale) {
    return rotate(angle).translate(xOffset, yOffset).scale(scale);
  }


  /**
   * function for manipulating (rotating) the immutable MapLayer
   * 
   * @see common.datatypes.Waypoint
   * @see common.objects.Member#lSense()
   *
   * @param angle The angle (in degrees) to rotate the Waypoints in a clockwise (positive)
   *        counterclockwise (negative) aspect
   * 
   * @return A new MapLayer with the applied transformation
   */
  private MapLayer rotate(double angle) {

    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    double sinA = Math.sin(Math.toRadians(angle));
    double cosA = Math.cos(Math.toRadians(angle));

    double oldX;
    double oldY;
    double newX;
    double newY;

    trackedRotation = trackedRotation + angle;

    for (Waypoint w : liDARRead) {
      oldX = w.getX();
      oldY = w.getY();
      newX = oldX * cosA - oldY * sinA;
      newY = oldX * sinA + oldY * cosA;
      res.add(new Waypoint(newX, newY));
    }
    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(),
        trackedScale, trackedOpens);
  }


  /**
   * function for manipulating (translating) the immutable MapLayer
   * 
   * @see common.datatypes.Waypoint
   * @see common.objects.Member#lSense()
   *
   * @param xOffset The value to translate (move) the Waypoints in a 'East' (positive) 'West'
   *        (negative) axis
   * @param yOffset The value to translate (move) the Waypoints in a 'North' (positive) 'South'
   *        (negative) axis
   * 
   * @return A new MapLayer with the applied transformation
   */
  private MapLayer translate(double xOffset, double yOffset) {

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
    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(),
        trackedScale, trackedOpens);
  }

  /**
   * function for manipulating (scaling) the immutable MapLayer
   * 
   * @see common.datatypes.Waypoint
   * @see common.objects.Member#lSense()
   *
   * @param scale The value to scale the Waypoints in a 'larger' (positive) 'smaller' (negative)
   *        aspect relative to 1.0
   * 
   * @return A new MapLayer with the applied transformation
   */
  public MapLayer scale(double scale) {
    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    trackedScale = trackedScale + scale;

    for (Waypoint w : liDARRead) {
      res.add(new Waypoint(w.getX() * scale, w.getY() * scale));
    }
    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(),
        trackedScale, trackedOpens);
  }


  /**
   * function for manipulating the immutable MapLayer this function adds a number of 'open'
   * Waypoints to the return so that when it is added to the map suitable open vertices will also be
   * added. This operate is called in-line as part of Map.amalgamateLayer(MapLayer) and thus;
   * *Should Not* need to be used separately. Additionally to this the operation is guarded so that
   * it can only be applied once to a Layer as it is O(n) per application resulting in a O(!) chain.
   * 
   * @see common.datatypes.Waypoint
   * @see common.datatypes.map.griddedMap.Vertex
   * @see common.datatypes.map.griddedMap.BlockedVertex
   * @see common.objects.Member#lSense()
   * @see common.datatypes.map.Map#amalgamateLayer(MapLayer)
   *
   * @return A new MapLayer with the addition of the open Waypoints
   */
  protected MapLayer addOpens() {
    ArrayList<Waypoint> res = new ArrayList<Waypoint>();

    if (!trackedOpens) {
      double xOffset = this.trackedCentre.getX();
      double yOffset = this.trackedCentre.getY();

      double i;
      double step = 1.0;

      for (Waypoint w : liDARRead) {
        double thisX = w.getX();
        double thisY = w.getY();
        i = Math.hypot(w.getX() - xOffset, w.getY() - yOffset) / step;
        for (double j = 1; j < i; j = j + step) {
          double scaledX = (thisX - xOffset) * (j / i);
          double scaledY = (thisY - yOffset) * (j / i);
          res.add(new Waypoint((scaledX + xOffset), (scaledY + yOffset), false));
        }
        res.add(w);
      }

    } else {
      res = liDARRead;
    }
    trackedOpens = true;
    return new MapLayer(res, trackedRotation, trackedCentre.getX(), trackedCentre.getY(),
        trackedScale, trackedOpens);
  }

  /**
   * Access method for getting the list of Waypoints from the MapLayer
   * 
   * @see common.datatypes.map.MapLayer
   * @see common.datatypes.Waypoint
   * @see common.datatypes.map.griddedMap.Vertex
   * @see common.datatypes.map.griddedMap.BlockedVertex
   *
   * @return Collection (ArrayList) of Waypoints that are either 'open' or 'blocked'
   */
  public ArrayList<Waypoint> getWaypoints() {
    return liDARRead;
  }

  @Override
  public Iterator<Waypoint> iterator() {
    return new LiDARIterator();
  }
  

  class LiDARIterator implements Iterator<Waypoint> {
    int current = 0;

    public boolean hasNext() {
      if (current < liDARRead.size()) {
        return true;
      } else {
        return false;
      }
    }

    public Waypoint next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return liDARRead.get(current++);
    }
  }

}
