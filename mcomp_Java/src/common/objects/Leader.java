package common.objects;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import common.datatypes.Waypoint;
import common.datatypes.path.Path;
import common.interfaces.Connectable;
import common.interfaces.Contactable;
import common.interfaces.Instructable;
import common.interfaces.Updateable;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * 
 *         DOCME Still needs work but is a rough draft 
 *         A leader must be able to pathfind on a map collated by its members 
 *         A leader must be able to handle the merging of two herds A leader
 *         A leader must be able to respond to method calls from its members         
 *
 */
public class Leader extends UnicastRemoteObject implements Instructable, Connectable, Updateable, Contactable {
  private int portNumber;
  private String serverName;
  private Registry r;

  private ArrayList<Member> herdMembers; //FIXME when a leader is first established, it should collect herd information from the member that spawned it

  InetAddress[] addresses;
  InetAddress loopback = InetAddress.getLoopbackAddress();

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

    // Try to shut down a server and do nothing if it fails as there isn't one running
    // May be factored into separate shutdownServer() method for neatness/outside access
    try {
      r.unbind(this.serverName);
      unexportObject(r, true);
    } catch (Exception e) {
    }

    try {
      r = LocateRegistry.getRegistry(this.portNumber);
      r.rebind(this.serverName, this);

      // Switch Wifi to infrastructure Mode - will probably set to infrastructure by default

      System.out.println(">>The leader process started succesfully<<");
    } catch (Exception e) {
      System.err.println(">>Leader process failed to start:<<");
      e.printStackTrace();
    }
  }

  /**
   * Polls the host of the Leader and collects the IP address of all reachable interfaces. If an
   * address cannot be reached, return the loopback address.
   * 
   * @return The array of IP addresses
   */
  @Override
  public InetAddress[] getAddress() {
    try {
      addresses = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
    } catch (UnknownHostException e) {
      e.printStackTrace();
      addresses[0] = loopback;
    }
    return addresses;
  }

  /**
   * DOCME
   * 
   * @return
   */
  @Override
  public InetAddress publishAddress() {
    // TODO For each member in Herd, RMI the address to each Member
    for (Member m : herdMembers) {
      // send the leaders IP
    }
    return null;
  }

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

  /**
   * DOCME
   */
  @Override
  public boolean register(Member m) {
    return false;
    // TODO Auto-generated method stub
    // used to register a client for server/client/mvc
    // Likely to take a member and add them to the 'registered' list??
  }

  /**
   * DOCME
   */
  @Override
  public boolean deregister(Member m) {
    return false;
    // TODO Auto-generated method stub
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
