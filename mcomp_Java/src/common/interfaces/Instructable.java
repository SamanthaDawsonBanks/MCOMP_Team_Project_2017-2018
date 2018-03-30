package common.interfaces;

import common.datatypes.Waypoint;
import common.datatypes.path.Path;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Instructable interface
 * 
 *         An object is instructable if it is capable of handling a request to find a path to a
 *         destination.
 * 
 */

public interface Instructable {

  public Path pathfind(Waypoint w); // Not sure where we decide on representing success or failure.
  
  public Boolean go();//FIXME needs better name and better sig

}
