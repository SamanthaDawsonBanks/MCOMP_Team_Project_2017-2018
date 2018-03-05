/**
 * 
 */
package common.interfaces;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Ryan Shoobert (15812407)
 *
 */
public interface Rmiable extends Remote {

  InetAddress publishAddress();
  
}
