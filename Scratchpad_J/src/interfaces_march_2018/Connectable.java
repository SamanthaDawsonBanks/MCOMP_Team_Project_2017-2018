package interfaces_march_2018;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Connectable interface
 * 
 * An object is connectable if it assumes the role of an RMI server,
 * meaning it can accept connections from multiple clients who wish
 * to register their methods with the RMI registry.
 * 
 */

public interface Connectable {

	public void register();
	public void deregister();
	
}
