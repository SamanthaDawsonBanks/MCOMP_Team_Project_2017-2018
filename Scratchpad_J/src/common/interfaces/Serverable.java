package common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Ryan Shoobert (15812407)
 *
 */
public interface Serverable extends Remote {

  /**
   * 
   * Example method
   * 
   * @param a The first Addend
   * @param b The last Addend (number to be added to 'a')
   * @return the result of adding the two numbers
   * @throws RemoteException
   */
  int add(int a, int b) throws RemoteException;
  
}
