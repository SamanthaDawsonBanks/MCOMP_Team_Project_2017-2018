/**
 * 
 */
package leader;

import java.rmi.RemoteException;
import common.objects.Leader;

  /**
   * 
   * @author Ryan Shoobert (15812407)
   * 
   * Main entry point for starting an instance of the server. To create this, a port number
   * and a name for the instance must be parsed in before the start method can be called
   * to set up a registry and bind behaviour for clients to access.
   * 
   */
  public class LeaderMain {
    public static void main(String[] args) {
      try {
        new Leader(1099, "Server").start();
      } catch (RemoteException e) {
        //TODO implement a method of handling a failed Leader creation 
        e.printStackTrace();
      }
    }
  }
