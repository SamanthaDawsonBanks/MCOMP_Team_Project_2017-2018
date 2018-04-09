package common.interfaces;

import java.rmi.RemoteException;

/**
 * An object is notifyable if is able to be alerted that the model in the MVC has changed. Typically
 * the client will be a GUI.
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
 * @see common.datatypes.Ability#VIEWER
 *
 */

public interface Notifiable {

  /**
   * Notification method for informing a viewer that the state has changed; and this to request new
   * data from the model (MVC)
   * 
   * @see common.datatypes.Ability
   * @see common.datatypes.map.griddedMap.GriddedMap
   * @see common.datatypes.map.MapLayer
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @throws RemoteException RMI between Member-Leader
   */
  public void notifyOfChange() throws RemoteException;

}
