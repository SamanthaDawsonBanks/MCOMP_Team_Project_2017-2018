package interfaces_march_2018;

import common.datatypes.Waypoint;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * @author Steve
 * 
 * Driveable interface
 * 
 * A driveable object can take a given Waypoint relative to its position and
 * drive to that location.
 */

public interface Driveable {

	public Waypoint drive (Waypoint w);
	
}
