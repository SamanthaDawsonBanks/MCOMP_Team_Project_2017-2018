package interfaces_march_2018;

import common.datatpyes.Waypoint;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Directable interface
 * 
 * A directable object is one that can accept input that defines a final
 * goal or destination for the Herd to attempt to pathfind to.
 */

public interface Directable {

	public boolean setDestination(Waypoint w); 
	
}
