package common.interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Herd;

/**
 * An object is groupable if it can be collected together into a herd
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
public interface Groupable {

  /**
   * Client side of the client (member) / server (leader) connection used to instruct a member to
   * request to join the provided herd
   * 
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @param newHerd A Herd object holding the leader to be contacted / joined to
   * 
   * @return Boolean stating the result; True = success
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  public boolean joinHerd(Herd newHerd) throws RemoteException;// FIXME herd object or leader ref??

  /**
   * Access method for getting the list of abilities from the member instance
   * 
   * @see common.objects.Herd
   * @see common.objects.Leader
   * @see common.datatypes.Ability
   *
   * @return Collection (ArrayList) Abilities (enum) that this member has
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  ArrayList<Ability> getAbilities() throws RemoteException;

  /**
   * Access method for getting the local member copy of the herd data
   * 
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return Herd data including path, map, members and destination
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  Herd getLocalHerdData() throws RemoteException;

  /**
   * Access method for setting the local member copy of the herd data
   * 
   * @see common.objects.Herd
   * @see common.objects.Leader
   * @see common.datatypes.Ability
   *
   * @param leaderHerd The Leaders copy of Herd data including path, map, members and destination
   *
   * @return Herd data including path, map, members and destination
   * 
   * @throws RemoteException RMI between Member-Leader
   */
  Herd updateLocalHerdInfo(Herd leaderHerd) throws RemoteException;
}
