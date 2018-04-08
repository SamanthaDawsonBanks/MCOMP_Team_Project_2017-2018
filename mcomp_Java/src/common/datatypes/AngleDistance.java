package common.datatypes;

/**
 * Immutable Tuple for holding an Angle (theta) and distance representation of a Waypoint
 * 
 * @author David Avery 15823926
 * @author Stephen Pope 15836791
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.datatypes.Waypoint
 *
 */
public class AngleDistance {
  double theta;
  double distance;

  /**
   * Constructor
   * 
   * @param t Angle (in degrees) offset from 0 (x axis) in a counter-clockwise aspect
   * @param d Distance (in centimetres) from the origin to the date point
   * 
   */
  AngleDistance(double t, double d) {
    theta = t;
    distance = d;
  }

  /**
   * Access method for getting theta value
   * 
   * @return Theta value in degrees as a double
   * 
   */
  double getTheta() {
    return theta;
  }

  /**
   * Access method for getting distance value
   * 
   * @return Distance value in centimetres as a double
   * 
   */
  double getDistance() {
    return distance;
  }


}
