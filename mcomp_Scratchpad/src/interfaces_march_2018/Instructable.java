package interfaces_march_2018;

import common.datatypes.Waypoint;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Instructable interface
 * 
 * An object is instructable if it is capable of handling a request to
 * find a path to a destination.
 * 
 */

public interface Instructable {

	public Waypoint pathfind(Waypoint w); //Not sure where we decide on representing success or failure.
	
}
