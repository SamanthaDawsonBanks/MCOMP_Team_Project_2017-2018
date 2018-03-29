/**
 * 
 */
package leader;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.objects.Leader;
import member.MemberMain;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * @author David Avery 15823926
 * 
 * Main entry point for starting an instance of the leader process. 
 * To create this, a port number and a name for the instance must 
 * be parsed in. A connection is then established with the registry 
 * where members will be able to invoke methods on the leader.
 * 
 */
public class LeaderMain {
  private static final Logger LOGGER = Logger.getLogger(LeaderMain.class.getName());

  public static void main(String[] args) {
    LOGGER.log( Level.INFO, "LeaderMain starting");
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
