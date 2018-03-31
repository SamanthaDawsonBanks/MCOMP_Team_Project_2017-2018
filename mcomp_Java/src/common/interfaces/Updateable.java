package common.interfaces;

import java.rmi.RemoteException;
import common.objects.Herd;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 *
 *         Updateable interface
 * 
 *         An object is updateable if it represents the model in the MVC pattern.
 * 
 */

public interface Updateable {

  public void updateModel() throws RemoteException; //FIXME this probably takes some info
  
  //there will be many methods that will do the updates
    //e.g robot is now at this location
  
  public Herd getHerdState() throws RemoteException; //returns the current state

}
