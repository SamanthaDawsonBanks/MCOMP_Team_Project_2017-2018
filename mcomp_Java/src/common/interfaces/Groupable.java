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
 * @see common.datatypes.Ability
 *
 */
public interface Groupable {

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

  boolean kill(String log) throws RemoteException;
}
