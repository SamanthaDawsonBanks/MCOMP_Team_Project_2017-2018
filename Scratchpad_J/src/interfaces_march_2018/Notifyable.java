package interfaces_march_2018;

/** 
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Notifyable interface
 * 
 * An object is notifyable if is able to alert the model in the MVC
 * pattern to a request for new or mutated state. Typically the client
 * will be a GUI.
 * 
 */

public interface Notifyable {
	
	public void notifyModel();

}
