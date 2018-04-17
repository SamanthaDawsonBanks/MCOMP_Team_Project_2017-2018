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
  
  public static boolean stayingAlive = true;

  private static RemoteLeader l;

  private static int portNumber = 1099;
  private static String serverName = "HerdLeader";
  private static Registry registry;

  public static void main(String[] args)
      throws AccessException, RemoteException, NotBoundException, MalformedURLException {

    LOGGER.log(Level.INFO, "LeaderMain starting");

    try {
      LOGGER.log(Level.INFO, "Creating RMI Registry");
      registry = java.rmi.registry.LocateRegistry.createRegistry(portNumber);
    } catch (RemoteException e) {
      LOGGER.log(Level.WARNING, "Registry already exists - Getting");
      registry = java.rmi.registry.LocateRegistry.getRegistry(portNumber);
    }

    LOGGER.log(Level.INFO, "Constructing Leader");
    l = new Leader();// args - 1099, "HerdLeader");

    LOGGER.log(Level.INFO, "Binding l as: {0}", serverName);
    Naming.rebind("rmi://192.168.25.42/" + serverName, l);


    listAllBoundRMINames(registry);

    // only for testing??
    // LOGGER.log(Level.INFO, "unexporting Leader l");
    // UnicastRemoteObject.unexportObject(l, true);
    // LOGGER.log(Level.INFO, "unbinding HerdLeader");
    // registry.unbind(serverName);


    LOGGER.log(Level.INFO, "End of LeaderMain");
    
    while (stayingAlive) {
      
    }
    LOGGER.log(Level.SEVERE, "Killed, Exiting");

  }


  private static void listAllBoundRMINames(Registry r) throws AccessException, RemoteException {
    LOGGER.log(Level.INFO, "Attempting to list bound names");
    StringBuilder namesOutput = new StringBuilder();
    for (final String name : r.list()) {
      namesOutput.append("\n").append(name);
    }
    LOGGER.log(Level.INFO, namesOutput.toString());
  }

}
