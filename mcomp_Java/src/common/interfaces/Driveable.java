package common.interfaces;

import java.rmi.RemoteException;
import common.datatypes.Waypoint;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Driveable interface
 * 
 *         A driveable object can take a given Waypoint relative to its position and drive to that
 *         location.
 */

public interface Driveable {

  public Waypoint drive(Waypoint w) throws RemoteException;

}
