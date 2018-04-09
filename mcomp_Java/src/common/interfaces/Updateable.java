package common.interfaces;

import java.rmi.RemoteException;
import common.objects.Herd;

/**
 * An object is updateable if it represents the 'model' / data storage holder object (Leader) in the
 * MVC pattern.
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
public interface Updateable {

  /**
   * Update method for setting the current state of the model
   * 
   * @see common.objects.Herd
   * @see common.objects.Leader
   * 
   * @param newHerdData Herd object containing the Member/Ability collections, Map, Destination, and Path with new info
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public void updateModel(Herd newHerdData) throws RemoteException;

  /**
   * Access method for getting the current state of the model typically called in response to a
   * notify call
   * 
   * @see common.objects.Herd
   * @see common.objects.Leader
   * @see common.interfaces.Notifiable
   * @see common.datatypes.Ability
   * 
   * @return Herd object containing the Member/Ability collections, Map, Destination, and Path
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public Herd getState() throws RemoteException;
}
