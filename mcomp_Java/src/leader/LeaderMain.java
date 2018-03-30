/**
 * 
 */
package leader;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.interfaces.RemoteLeader;
import common.objects.Leader;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * @author David Avery 15823926
 * 
 *         Main entry point for starting an instance of the leader process. To create this, a port
 *         number and a name for the instance must be parsed in. A connection is then established
 *         with the registry where members will be able to invoke methods on the leader.
 * 
 */
public class LeaderMain {
  private static final Logger LOGGER = Logger.getLogger(LeaderMain.class.getName());

  private static RemoteLeader l;

  private static int portNumber = 1099;
  private static String serverName = "LeaderRMIServer";
  private static Registry registry;

  public static void main(String[] args) throws AccessException, RemoteException, NotBoundException, MalformedURLException {

    LOGGER.log(Level.INFO, "LeaderMain starting");

    try {
      registry = java.rmi.registry.LocateRegistry.createRegistry(portNumber);
    } catch (RemoteException e) {
      LOGGER.log(Level.WARNING, "Registery already exists - Getting");
      registry = java.rmi.registry.LocateRegistry.getRegistry(portNumber);
    }

    LOGGER.log(Level.INFO, "Calling Leader Constructor");
    l = new Leader();// args - 1099, "HerdLeader");
    LOGGER.log(Level.INFO, "Leader Constructed");

    LOGGER.log(Level.INFO, "Leader Constructed");
    Naming.rebind(serverName, l);
    LOGGER.log(Level.INFO, "Leader Constructed");
    
    
    listAllBoundRMINames(registry);
    
    
    //only for testing??
    LOGGER.log(Level.INFO, "Leader Constructed");
    UnicastRemoteObject.unexportObject(l, true);
    registry.unbind(serverName);
    LOGGER.log(Level.INFO, "Leader Constructed");

    
    LOGGER.log(Level.INFO, "End of LeaderMain");
  }


  private static void listAllBoundRMINames(Registry r) throws AccessException, RemoteException {
    String[] boundNames;
      LOGGER.log(Level.INFO, "Attempting to list bound names");
      boundNames = r.list();

      String namesOutput = "";
      for (final String name : boundNames) {
        namesOutput = namesOutput + name + "\n";
      }
      LOGGER.log(Level.INFO, namesOutput);
  }

}
