package common.interfaces;

import java.rmi.RemoteException;
import common.datatypes.Waypoint;

/**
 * A driveable object can take a given Waypoint relative to its position and drive to that location.
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
 * @see common.datatypes.Ability#DRIVER
 *
 */

public interface Driveable {

  /**
   * Processes a section of the map for amalgamation as part of a distributed call to the members
   * 
   * @see common.datatypes.map.griddedMap.GriddedMap
   * @see common.datatypes.map.MapLayer
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param w The RELATIVE x/y coordinates tuple as a Waypoint to be driven to
   * 
   * @return The (relative to the starting point) Waypoint that was reached. If equal to the input
   *         then successful; if not then the distance travelled before interruption / avoidance /
   *         collision
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public Waypoint drive(Waypoint w) throws RemoteException;

}
