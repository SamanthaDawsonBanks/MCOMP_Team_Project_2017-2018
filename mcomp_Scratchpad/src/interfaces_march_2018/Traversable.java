package interfaces_march_2018;

import common.interfaces.Waypoint;
import common.interfaces.map.griddedMap.Vertex;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Traversable interface
 * 
 * An object that is traversable is able be queried for sections of itself
 * so that they can be analysed by a pathfinding algorithm.
 * 
 */

public interface Traversable {
	public Vertex getVertex(Waypoint w);
	public void setBlocked();
	public int getX();
	public int getY();
}
