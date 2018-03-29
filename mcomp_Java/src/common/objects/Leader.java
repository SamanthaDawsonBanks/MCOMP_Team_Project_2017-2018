package common.objects;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.datatypes.Waypoint;
import common.datatypes.path.Path;
import common.interfaces.Connectable;
import common.interfaces.Contactable;
import common.interfaces.Instructable;
import common.interfaces.Updateable;
import leader.LeaderMain;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * @author David Avery 15823926
 * 
 *         DOCME Still needs work but is a rough draft 
 *         A leader must be able to pathfind on a map collated by its members 
 *         A leader must be able to handle the merging of two herds A leader
 *         A leader must be able to respond to method calls from its members         
 *
 */
public class Leader extends UnicastRemoteObject implements Instructable, Connectable, Updateable, Contactable {
  private static final Logger LOGGER = Logger.getLogger(LeaderMain.class.getName());
  
  private int portNumber;
  private String serverName;
  private Registry r;

  InetAddress[] addresses;
  InetAddress loopback;
  
  private ArrayList<Member> herdMembers; //FIXME when a leader is first established, it should collect herd information from the member that spawned it
  
  /**
   * Constructor for the leader object. This will take a port number and a name and store these in
   * local variables.
   * 
   * The leader then attempts to connect to the running registry where it will be able to share it's
   * method stubs with connected members.
   * 
   * @param portNumber The port number that the leader will communicate on
   * @param serverName The assigned name to the running instance //This may later change to the
   *        HerdID
   */
  public Leader(int portNumber, String serverName) throws RemoteException {
    this.portNumber = portNumber;
    this.serverName = serverName;

    // Switch Wifi to infrastructure Mode - will probably set to infrastructure by default

//    InetAddress[] addresses;
//    InetAddress loopback = InetAddress.getLoopbackAddress();
    
    // Try to shut down a server and do nothing if it fails as there isn't one running
    unbindFromRMIServer();
    bindToRMIServer();
    
  }

  private boolean bindToRMIServer() {
    //should always be proceeded by a shutdown reg call //TODO add as inline call?
    LOGGER.log( Level.INFO, "Binding to  RMI Registery Server");
    try {
      r = LocateRegistry.getRegistry(this.portNumber);
      r.rebind(this.serverName, this);
      LOGGER.log( Level.INFO, "RMI Registery Server Bound");
      return true;
    } catch (Exception e) {
      LOGGER.log( Level.SEVERE, "RMI Registery Server Failed to Bind");
      e.printStackTrace();
      return false;
    }

  }
  
  private boolean unbindFromRMIServer() {
    LOGGER.log( Level.INFO, "UnBinding from RMI Registery Server");
    try {
      r.unbind(this.serverName);
      unexportObject(r, true);
      LOGGER.log( Level.INFO, "RMI Registery Server UnBound");
      return true;
    } catch (Exception e) {
      LOGGER.log( Level.INFO, "No RMI Registery Server was Bound");
      return false;
    }
  }
  
  
  
  
  /**
   * Polls the host of the Leader and collects the IP address of all reachable interfaces. If an
   * address cannot be reached, return the loopback address.
   * 
   * @return The array of IP addresses
   */
//  @Override
//  public InetAddress[] getAddress() {
//    try {
//      addresses = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
//    } catch (UnknownHostException e) {
//      e.printStackTrace();
//      addresses[0] = loopback;
//    }
//    return addresses;
//  }

  /**
   * DOCME
   * 
   * @return
   */
//  @Override
//  public InetAddress publishAddress() {
//    // TODO For each member in Herd, RMI the address to each Member
//    for (Member m : herdMembers) {
//      // send the leaders IP
//    }
//    return null;
//  }

  /**
   * When called this method will return a list of all registered members currently known to the
   * leader of the herd.
   * 
   * @return The current state of the members list
   */
  public Collection<Member> getMemebers() {
    //potentially depreciated
    return herdMembers;
  }

  /**
   * When two leaders come into contact with each other and want to merge herds, **stuff** will
   * happen that results in an elected leader for the "new" herd as well as the transitioning of
   * current members from the "old" one.
   */
  @Override
  public void leaderDiscussMerge() {
    // TODO Auto-generated method stub
    // Will be related to herd merging
    throw new UnsupportedOperationException("method not implemented");
  }

  /**
   * DOCME
   */
  @Override
  public void updateModel() {
    // TODO Auto-generated method stub
    // Won't be update model
  }
  
  @Override
  public void getState() {
    // TODO Auto-generated method stub
    // bundle up all state and send it to the client
    // displayable needs current map and destination
  }

  /**
   * DOCME
   */
  @Override
  public boolean register(Member m) {
    return this.herdMembers.add(m);
    // used to register a client for server/client/mvc
    // Likely to take a member and add them to the 'registered' list??
  }

  /**
   * DOCME
   */
  @Override
  public boolean deregister(Member m) {
    return this.herdMembers.remove(m);  //not sure that's right - will look into
    // As with register but removing??
  }

  /**
   * DOCME
   */
  @Override
  public Path pathfind(Waypoint w) {
    // TODO Auto-generated method stub
    // An internal call to actual pathfinding
    return null;
  }
}
