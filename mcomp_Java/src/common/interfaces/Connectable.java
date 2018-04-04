package common.interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;
import common.objects.Herd;

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

  /**
   * When a member joins a herd, it must register itself with the leader of that herd so that it can
   * Receive instructions from it.
   * 
   * @param joiningMember The member of the herd that is registering itself with the leader
   * @return Returns true if the member was registered successfully, False if it did not
   * @throws RemoteException 
   */
  public ArrayList<RemoteMember> register(RemoteMember joiningMember) throws RemoteException;

  /**
   * When leaving a herd (normally in a herd merge), the member must first unregister itself with
   * the current leader so that it can no longer recieve instruction from it.
   * 
   * @param leavingMember The member of the herd that is leaving and this unregistering with the
   *        leader.
   * @return Returns true if the member unregistered with the leader, False if it did not
   * @throws RemoteException 
   */
  public ArrayList<RemoteMember> deregister(RemoteMember leavingMember) throws RemoteException;

  public ArrayList<RemoteMember> getMemebers() throws RemoteException;

}
