package common.interfaces;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Connectable interface
 * 
 *         An object is connectable if it assumes the role of an RMI server, meaning it can accept
 *         connections from multiple clients who wish to register their methods with the RMI
 *         registry.
 * 
 */

public interface Connectable {

  public void register();

  public void deregister();

}
