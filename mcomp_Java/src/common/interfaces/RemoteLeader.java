/**
 * 
 */
package common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import common.datatypes.Waypoint;
import common.datatypes.path.Path;

/**
 * @author David Avery 15823926
 *
 */
public interface RemoteLeader extends Remote, Instructable, Connectable, Updateable, Contactable {

  //Instructable
  @Override
  public Path pathfind(Waypoint w) throws RemoteException;
  
  @Override
  public Boolean go() throws RemoteException;

  //Connectable
  @Override
  public boolean register(RemoteMember joiningMember) throws RemoteException;

  @Override
  public boolean deregister(RemoteMember leavingMember) throws RemoteException;
  
  
  //Updateable
  @Override
  public void updateModel() throws RemoteException; //FIXME this probably takes some info
  
  @Override
  public void getHerdState() throws RemoteException; //returns the current state

  
  //Contactable
  @Override
  public void leaderDiscussMerge() throws RemoteException; // TODO obviously a lot of this needs refining.
  
}
