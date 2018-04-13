package common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Ability;
import common.datatypes.Waypoint;
import common.objects.Herd;

/**
 * Unification of the 'Member' methods for purposes of RMI/Remote identification
 * 
 * Note: as an extension this (and RemoteLeader could be inverted and replaced with the decorator
 * pattern)
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.objects.Member
 * @see common.objects.Herd
 * @see common.objects.Leader
 * @see common.interfaces.RemoteLeader
 *
 */
public interface RemoteView
    extends Remote, Serializable, Drawable, Directable, Groupable, Notifiable, Transferable {

  // General
  public void RMITest() throws RemoteException;

  // Drawable

  // Directable
  @Override
  public boolean setDestination(Waypoint w) throws RemoteException;

  // Groupable
  @Override
  public boolean joinHerd(Herd newHerd) throws RemoteException;

  @Override
  ArrayList<Ability> getAbilities() throws RemoteException;

  @Override
  Herd getLocalHerdData() throws RemoteException;

  @Override
  Herd updateLocalHerdInfo(Herd leaderHerd) throws RemoteException;

  // Notifiable
  @Override
  public void notifyOfChange() throws RemoteException;

  // Transferable

}


