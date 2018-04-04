package common.interfaces;

import java.rmi.RemoteException;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Notifyable interface
 * 
 *         An object is notifyable if is able to be alerted that the model in the MVC has changed.
 *         Typically the client will be a GUI.
 * 
 */

public interface Notifiable extends RemoteMember {

  public void notifyOfChange() throws RemoteException;

}
