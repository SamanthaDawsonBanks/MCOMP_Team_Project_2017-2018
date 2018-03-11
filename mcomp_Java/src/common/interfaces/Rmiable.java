/**
 * 
 */
package common.interfaces;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import common.objects.Member;

/**
 * 
 * @author Ryan Shoobert (15812407)
 *
 */
public interface Rmiable extends Remote {

  InetAddress publishAddress();

  /**
   * NOTE: have left them but they will be moved to a different interface when 
   *       discussed with group.
   * 
   * A member 'm' will join the herd by registering with the leader.
   * Once added to the members list, the members will be able to invoke
   * methods on the leader.
   * 
   * @param m The member object to be added
   * @return True if the member was added successfully; False if it was not
   */
//  public boolean joinHerd(Member m);
  
  /**
   * When a member wants to leave the herd, it must notify the leader so that
   * it can be removed from all of the lists it is currently held on.
   * 
   * @param m The member that wants to leave
   * @return True if the member left successfully; False if it did not
   */
//  public boolean leaveHerd(Member m);
  
}
