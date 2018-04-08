package common.interfaces;

import java.rmi.RemoteException;

/**
 * An object is instructable if it is capable of handling a request to find a path to a destination.
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
public interface Instructable {

  /**
   * Instructs the object (typically Leader) to use the stored Map and Destination and attempt to
   * find a valid path; the path will be stored in the Leader.herd.path variable
   * 
   * @see common.datatypes.map.griddedMap.GriddedMap
   * @see common.datatypes.map.MapLayer
   * @see common.objects.Herd
   * @see common.objects.Leader
   * @see pathfinding.AStar //TODO adjust for refactored names
   *
   * @return Boolean stating Success (True) or failure (False)
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public boolean pathfind() throws RemoteException; // Not sure where we decide on representing
                                                    // success or failure.

  /**
   * Instructs the object (typically Leader) to use the stored Map, Destination, and Path to in-turn
   * instruct the Driveable Members to start moving
   * 
   * @see common.objects.Member
   * @see common.objects.Member#drive(common.datatypes.Waypoint)
   * @see common.datatypes.Ability#DRIVER
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Boolean indicating initiation (True) or hold state (False)
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public Boolean go() throws RemoteException;// FIXME needs better name and better sig

}
