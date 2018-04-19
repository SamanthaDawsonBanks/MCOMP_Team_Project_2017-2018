package common.interfaces;

import java.rmi.RemoteException;
import common.objects.Herd;
import common.objects.Leader;

/**
 * An object is promotable if it has the ability to stand in an election and become the Leader of a
 * Herd, creating a new Leader process.
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

public interface Promotable {

  /**
   * Action method for instructing the object (Member) to spawn a new Leader process and this become
   * the leader
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param h A Herd object holding the members and herd to become leader of
   * 
   * @return True if the leader started
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public boolean becomeLeader(Herd h) throws RemoteException;
  // object to the Herd?

}
