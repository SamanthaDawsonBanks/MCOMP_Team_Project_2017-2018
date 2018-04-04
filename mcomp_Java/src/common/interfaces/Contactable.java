package common.interfaces;

import java.rmi.RemoteException;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Contactable interface
 *
 *         An object is contactable if it able to handshake with a Leader from another Herd.
 * 
 */

public interface Contactable extends RemoteLeader {

  public void leaderDiscussMerge() throws RemoteException; // TODO obviously a lot of this needs refining.

}
