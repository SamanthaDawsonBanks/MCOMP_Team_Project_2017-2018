package common.interfaces;

import java.rmi.RemoteException;
import common.objects.Herd;

/**
 * An object is contactable if it able to handshake with a Leader from another Herd.
 * 
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.objects.Herd
 * @see common.objects.Leader
 * @see common.interfaces.RemoteLeader
 *
 */
public interface Contactable {

  /**
   * Called remotely by another Leader process; by comparing the remote and local herds one leader
   * will be defined as the Primary the other as the Secondary. The secondary herd will be absorbed
   * by the primary. The secondary will pass off all of its members to the primary.
   * 
   * @see common.objects.Member
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param h A Herd object holding the remote data
   * 
   * @return boolean where True means the the remote will be the Primary or False means the the
   *         remote will be the Secondary
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public boolean leaderDiscussMerge(Herd h) throws RemoteException;
}
