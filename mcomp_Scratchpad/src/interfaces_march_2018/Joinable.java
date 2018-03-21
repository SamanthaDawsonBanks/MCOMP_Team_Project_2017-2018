package interfaces_march_2018;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Joinable interface
 * 
 * An object is joinable if it has the ability to accept new Members to its Herd.
 *
 */

public interface Joinable {

	public boolean joinHerd();//TODO leader info? Is this interface badly specified?
	public boolean notifyJoin();//TODO same as above, notify the leader of a join?
}
