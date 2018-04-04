/**
 * 
 */
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
import common.objects.Leader;

/**
 * @author David Avery 15823926
 *
 */
public interface RemoteMember extends Remote, Serializable, Bossable, Drawable, Directable,
    Driveable, Groupable, LSenseable, Notifiable, Promotable, Transferable {
  
  //General
  public void RMITest() throws RemoteException;

  // Bossable
  @Override
  public Map processMapLump(Herd h) throws RemoteException;// herd is the data holder for members, maps, and paths

  @Override
  public Path processPathLump(Herd h) throws RemoteException;// herd is the data holder for members, maps, and paths

  // Drawable

  // Directable
  @Override
  public boolean setDestination(Waypoint w) throws RemoteException;
  
  // Driveable
  @Override
  public Waypoint drive(Waypoint w) throws RemoteException;

  // Groupable
  @Override
  public boolean joinHerd(Herd newHerd) throws RemoteException;

  @Override
  ArrayList<Ability> getAbilities() throws RemoteException;

  @Override
  Herd getLocalHerdData() throws RemoteException;

  @Override
  Herd updateLocalHerdInfo(Herd leaderHerd) throws RemoteException;

  // LSenseable
  @Override
  public MapLayer lSense() throws RemoteException;

  // Notifiable
  @Override
  public void notifyOfChange() throws RemoteException;

  // Promotable
  @Override
  public Leader becomeLeader(Herd h) throws RemoteException; // TODO returns the initialised Leader object to the Herd?

  // ????Securable / Keyable????
  //@Override 
  //FIXME adjust for security interface
  public Object getPublicKey() throws RemoteException;

  // Transferable

}
