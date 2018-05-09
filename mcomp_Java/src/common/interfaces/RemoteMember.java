package common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Ability;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.MapLayer;
import common.datatypes.path.Path;
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
public interface RemoteMember extends Remote, Serializable, Bossable, Drawable, Directable,
    Driveable, Groupable, LSenseable, Notifiable, Promotable, Transferable {

  // General
  public void RMITest() throws RemoteException;

  // Bossable
  @Override
  public Map processMapLump() throws RemoteException;

  @Override
  public Path processPathLump() throws RemoteException;
  
  @Override
  public Path optimizePathLump() throws RemoteException;

  // Drawable

  // Directable
  @Override
  public boolean setDestination(Waypoint w) throws RemoteException;

  // Driveable
  @Override
  public Waypoint drive(Waypoint w) throws RemoteException;
  
  @Override
  public Waypoint getPos() throws RemoteException;
  
  // Groupable
  @Override
  ArrayList<Ability> getAbilities() throws RemoteException;

  @Override
  boolean kill(String log) throws RemoteException;

  // LSenseable
  @Override
  public MapLayer lSense() throws RemoteException;

  // Notifiable
  @Override
  public void notifyOfChange() throws RemoteException;

  // Promotable
  @Override
  public boolean becomeLeader(Herd h) throws RemoteException;

  // Transferable

}
