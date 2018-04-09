package common.interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * An object is connectable if it assumes the role of an RMI server, meaning it can accept
 * connections from multiple clients who wish to register their methods with the RMI registry.
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

public interface Connectable {

  /**
   * When a member joins a herd, it must register itself with the leader of that herd so that it can
   * Receive instructions from it.
   * 
   * @param joiningMember The member of the herd that is registering itself with the leader
   * 
   * @return The members group plus the joining member.
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public ArrayList<RemoteMember> register(RemoteMember joiningMember) throws RemoteException;

  /**
   * When leaving a herd (normally in a herd merge), the member must first unregister itself with
   * the current leader so that it can no longer receive instruction from it.
   * 
   * @param leavingMember The member of the herd that is leaving and this unregistering with the
   *        leader.
   * 
   * @return The members group minus the leaving member.
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public ArrayList<RemoteMember> deregister(RemoteMember leavingMember) throws RemoteException;

  /**
   * Access method for getting a collection of all members
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Collection of all Members
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public ArrayList<RemoteMember> getMemebers() throws RemoteException;

}
