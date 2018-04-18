/**
 * 
 */
package common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Waypoint;
import common.objects.Herd;
import member.ui.View;

/**
 * Unification of the 'Leader' methods for purposes of RMI/Remote identification
 * 
 * Note: as an extension this (and RemoteMember could be inverted and replaced with the decorator
 * pattern)
 * 
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.interfaces.RemoteMember
 * @see common.objects.Member
 * @see common.objects.Herd
 * @see common.objects.Leader
 *
 */
public interface RemoteLeader
    extends Remote, Serializable, Connectable, Contactable, Directable, Instructable, Updateable {

  // General
  public void RMITest() throws RemoteException;

  // Connectable
  @Override
  public ArrayList<RemoteMember> register(RemoteMember joiningMember) throws RemoteException;

  @Override
  public ArrayList<RemoteView> register(RemoteView view) throws RemoteException;

  @Override
  public ArrayList<RemoteMember> deregister(RemoteMember leavingMember) throws RemoteException;

  @Override
  public ArrayList<RemoteView> deregister(RemoteView leavingView) throws RemoteException;

  @Override
  public ArrayList<RemoteMember> getMemebers() throws RemoteException;

  @Override
  boolean kill(String log) throws RemoteException;

  // Contactable
  @Override
  public boolean leaderDiscussMerge(Herd h) throws RemoteException;

  // Directable
  @Override
  public boolean setDestination(Waypoint w) throws RemoteException;

  // Instructable
  @Override
  public boolean pathfind() throws RemoteException;

  @Override
  public Boolean go() throws RemoteException;

  // Updateable
  @Override
  public void updateModel(Herd newHerdData) throws RemoteException;

  @Override
  public Herd getState() throws RemoteException;

}
