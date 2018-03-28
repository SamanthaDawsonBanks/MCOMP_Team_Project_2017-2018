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
 * be parsed in. A connection is then established with the registry 
 * where members will be able to invoke methods on the leader.
 * 
 */
public class LeaderMain {
  public static void main(String[] args) {
    System.out.println("Leader Started");
    try {
      new Leader(1099, "HerdLeader");
    } catch (RemoteException e) {
      System.err.println("The leader process failed to start");
      e.printStackTrace();
    }
    while (true) { // loop to keep process running for testing
    }
  }
}
