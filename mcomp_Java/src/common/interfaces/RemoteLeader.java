/**
 * 
 */
package common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import common.datatypes.Waypoint;
import common.datatypes.path.Path;
import common.objects.Herd;

/**
 * @author David Avery 15823926
 *
 */
public interface RemoteLeader extends Remote, Serializable, Connectable, Contactable, Directable, Instructable, Updateable {

  //General
  public void RMITest() throws RemoteException;

  //Connectable
  @Override
  public boolean register(RemoteMember joiningMember) throws RemoteException;

  @Override
  public boolean deregister(RemoteMember leavingMember) throws RemoteException;
  
  //Contactable
  @Override
  public void leaderDiscussMerge() throws RemoteException; // TODO obviously a lot of this needs refining.

  //Directable
  @Override
  public boolean setDestination(Waypoint w) throws RemoteException;

  //Instructable
  @Override
  public boolean pathfind() throws RemoteException;
  
  @Override
  public Boolean go() throws RemoteException;

  //Updateable
  @Override
  public void updateModel(Herd newHerdData) throws RemoteException; //FIXME this probably takes some info
  
  @Override
  public Herd getState() throws RemoteException; //returns the current state

}