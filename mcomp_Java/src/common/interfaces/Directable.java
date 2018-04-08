package common.interfaces;

import java.rmi.RemoteException;
import common.datatypes.Waypoint;
import common.objects.Herd;

/**
 * A directable object is one that can accept input that defines a final goal or destination for the
 * object to attempt to pathfind to.
 * 
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.interfaces.RemoteMember
 * @see common.objects.Member
 * @see common.objects.Herd
 * @see common.objects.Leader
 * @see common.interfaces.RemoteLeader
 *
 */

public interface Directable {
  /**
   * Takes destination input and passes, or stores it
   * 
   * Note: Both RemoteLeader and RemoteMember implement this interface with Member passing the data
   * from the control to the leader and leader taking it and recording it in the herd
   * 
   * @param w The Waypoint (x/y) tuple holding the destination as coordinates
   * 
   * @return Boolean True for successfully set, False for an error / rejection
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public boolean setDestination(Waypoint w) throws RemoteException;

}
