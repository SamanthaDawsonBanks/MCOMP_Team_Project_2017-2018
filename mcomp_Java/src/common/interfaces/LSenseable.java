package common.interfaces;

import common.datatypes.map.MapLayer;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926 
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 *
 *         Senseable interface
 * 
 *         An object is Senseable if it is equipped to add detail to a Map with data from its
 *         sensors.
 * 
 */

public interface LSenseable {

  public MapLayer lSense();

}
