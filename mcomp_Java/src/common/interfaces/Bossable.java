package common.interfaces;

import java.rmi.RemoteException;
import common.datatypes.map.Map;
import common.datatypes.path.Path;
import common.objects.Herd;

/**
 * A bossable object is one that can receive instructions from the Leader of a Herd to process a
 * section of a particular item, either a segment of a Map to be augmented or a Path to be analysed.
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
 * @see pathfinding.AStar //TODO adjust for refactored names
 * @see common.datatypes.Ability#PROCESSOR
 *
 */
public interface Bossable {

  /**
   * Processes a section of the map for amalgamation as part of a distributed call to the members
   * 
   * @see common.datatypes.map.griddedMap.GriddedMap
   * @see common.datatypes.map.MapLayer
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param h A Herd object holding the map data to be processed
   * 
   * @return The section of the map processed
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public Map processMapLump(Herd h) throws RemoteException;

  /**
   * Processes a section of the path for pathfinding as part of a distributed call to the members
   * 
   * @see pathfinding.AStar //TODO adjust for refactored names
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param h A Herd object holding the path data to be processed
   * 
   * @return The section of the path processed
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public Path processPathLump(Herd h) throws RemoteException;

}
