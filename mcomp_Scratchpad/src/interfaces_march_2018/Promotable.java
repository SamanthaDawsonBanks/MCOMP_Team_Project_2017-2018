package interfaces_march_2018;
import common.objects.Member;
import common.objects.Leader;
/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Promotable interface
 * 
 * An object is promotable if it has the ability to stand in an
 * election and become the Leader of a Herd, creating a new Leader
 * process.
 * 
 */

public interface Promotable {

	public Leader becomeLeader(); //TODO returns the initialised Leader object to the Herd?
	
}
