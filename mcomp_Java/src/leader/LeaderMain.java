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
 * Main entry point for starting an instance of the leader process. 
 * To create this, a port number and a name for the instance must 
 * be parsed in before the start method can be called to set up a 
 * registry and bind behaviour for members to access.
 * 
 */
public class LeaderMain {
  public static void main(String[] args) {
    try {
      new Leader(1099, "HerdLeader").start();
    } catch (RemoteException e) {
      System.err.println("The leader process failed to start");
      e.printStackTrace();
    }
  }
}
