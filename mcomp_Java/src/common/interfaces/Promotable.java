package common.interfaces;

import java.rmi.RemoteException;
import common.objects.Herd;
import common.objects.Leader;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926 
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Promotable interface
 * 
 *         An object is promotable if it has the ability to stand in an election and become the
 *         Leader of a Herd, creating a new Leader process.
 * 
 */

public interface Promotable extends RemoteMember {

  public Leader becomeLeader(Herd h) throws RemoteException; // TODO returns the initialised Leader object to the Herd?

}
