package common.interfaces;

import java.rmi.RemoteException;
import common.datatypes.Waypoint;
import common.objects.Herd;

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
 * @see RemoteMember
 * @see Member
 * @see Herd
 * @see Leader
 * @see RemoteLeader
 *
 */

public interface Driveable {

  /**
   * Processes a section of the map for amalgamation as part of a distributed call to the members
   * 
   * @see GriddedMap
   * @see MapLayer
   * @see Herd
   * @see Leader
   *
   * @param w The RELATIVE x/y coordinates tuple as a Waypoint to be driven to
   * 
   * @return The (relative to the starting point) Waypoint that was reached. If equal to the input
   *         then successful; if not then the distance travelled before interruption / avoidance /
   *         collision
   * 
   * @throws RemoteException
   */
  public Waypoint drive(Waypoint w) throws RemoteException;

}
