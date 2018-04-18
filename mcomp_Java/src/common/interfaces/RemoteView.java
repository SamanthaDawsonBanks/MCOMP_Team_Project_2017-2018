package common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Ability;
import common.datatypes.Waypoint;
import common.objects.Herd;

/**
 * Unification of the 'View' methods for purposes of RMI/Remote identification
 * 
 * Note: as an extension this (and RemoteLeader could be inverted and replaced with the decorator
 * pattern)
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see EDIT this!!
 * 
 * @see common.objects.Member
 * @see common.objects.Herd
 * @see common.objects.Leader
 * @see common.interfaces.RemoteLeader
 *
 */
public interface RemoteView
    extends Remote, Serializable, Drawable, Notifiable, Transferable {

  // General
  public void RMITest() throws RemoteException;

  boolean kill(String log) throws RemoteException;

  // Drawable

  // Notifiable
  @Override
  public void notifyOfChange() throws RemoteException;

  // Transferable

}


