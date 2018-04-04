package common.interfaces;

import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Herd;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         An object is groupable if it can be collected together into a herd
 *
 */
public interface Groupable extends RemoteMember {
  public boolean joinHerd(Herd newHerd) throws RemoteException;

  ArrayList<Ability> getAbilities() throws RemoteException;

  Herd getLocalHerdData() throws RemoteException;

  Herd updateLocalHerdInfo(Herd leaderHerd) throws RemoteException;
}
