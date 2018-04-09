package common.interfaces;

import java.rmi.RemoteException;
import common.datatypes.map.MapLayer;

/**
 * An object is Senseable if it is equipped to add detail to a Map with data from its LOS (Line of
 * Sight) sensors.
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
 * @see common.datatypes.Ability#SENSOR
 *
 */

public interface LSenseable {

  /**
   * Instruction method for requesting another / new sensor reading
   * 
   * @see common.datatypes.Ability
   * @see common.datatypes.map.MapLayer
   * @see common.datatypes.Waypoint
   * @see common.datatypes.map.griddedMap.Vertex
   * @see common.datatypes.map.griddedMap.BlockedVertex
   *
   * @return MapLayer object containing a collection (ArrayList) of x/y tuples (Blocked Waypoints) to be added to the Map
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public MapLayer lSense() throws RemoteException;

}
