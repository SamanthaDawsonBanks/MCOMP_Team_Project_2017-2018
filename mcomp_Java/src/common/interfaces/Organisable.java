package common.interfaces;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Organisable interface
 * 
 *         An object is organisable if it supports the ability to hold an election and select a
 *         Leader.
 * 
 */

public interface Organisable {

  public RemoteMember electLeader();

}
