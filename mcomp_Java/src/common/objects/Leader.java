package common.objects;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import common.datatypes.Waypoint;
import common.interfaces.Connectable;
import common.interfaces.Contactable;
import common.interfaces.Directable;
import common.interfaces.Driveable;
import common.interfaces.Instructable;
import common.interfaces.LSenseable;
import common.interfaces.RemoteLeader;
import common.interfaces.RemoteMember;
import common.interfaces.Updateable;
import leader.LeaderMain;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * @author David Avery 15823926
 * 
 *         DOCME Still needs work but is a rough draft A leader must be able to pathfind on a map
 *         collated by its members A leader must be able to handle the merging of two herds A leader
 *         A leader must be able to respond to method calls from its members
 *
 */
public class Leader extends UnicastRemoteObject
    implements RemoteLeader, Instructable, Connectable, Directable, Updateable, Contactable {

  private static final Logger LOGGER = Logger.getLogger(LeaderMain.class.getName());

  private Herd herd;

//  private ArrayList<RemoteMember> ConnectedMembers = new ArrayList<RemoteMember>(); // FIXME
//                                                                                    // refactor this
//                                                                                    // into herd? as
//                                                                                    // herd.members?
//                                                                                    // or
//                                                                                    // herdmembers?

  private ArrayList<Member> herdMembers; // FIXME when a leader is first established, it should
                                         // collect herd information from the member that spawned it
  // TODO why a list of members and not a herd object?

  // private int portNumber;
  // private String serverName;
  // private Registry r;

  InetAddress[] addresses;
  InetAddress loopback;

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
  public Leader() throws RemoteException { // FIXME needs initialisation loop form member process
                                           // via RMI
    // args - int portNumber, String serverName) { - maybe herd?
    // this.portNumber = portNumber;
    // this.serverName = serverName;

    // Switch Wifi to infrastructure Mode - will probably set to infrastructure by default

    // InetAddress[] addresses;
    // InetAddress loopback = InetAddress.getLoopbackAddress();

    // Try to shut down a server and do nothing if it fails as there isn't one running
    // unbindFromRMIServer();
    // bindToRMIServer();

    
  }

  // private boolean bindToRMIServer() {
  // //should always be proceeded by a shutdown reg call //TODO add as inline call?
  // LOGGER.log( Level.INFO, "Binding to RMI Registery Server");
  // try {
  // r = LocateRegistry.getRegistry(this.portNumber);
  // r.rebind(this.serverName, this);
  // LOGGER.log( Level.INFO, "RMI Registery Server Bound");
  // return true;
  // } catch (Exception e) {
  // LOGGER.log( Level.SEVERE, "RMI Registery Server Failed to Bind");
  // e.printStackTrace();
  // return false;
  // }
  //
  // }
  //
  // private boolean unbindFromRMIServer() {
  // LOGGER.log( Level.INFO, "UnBinding from RMI Registery Server");
  // try {
  // r.unbind(this.serverName);
  // unexportObject(r, true);
  // LOGGER.log( Level.INFO, "RMI Registery Server UnBound");
  // return true;
  // } catch (Exception e) {
  // LOGGER.log( Level.INFO, "No RMI Registery Server was Bound");
  // return false;
  // }
  // }
  //



  /**
   * Polls the host of the Leader and collects the IP address of all reachable interfaces. If an
   * address cannot be reached, return the loopback address.
   * 
   * @return The array of IP addresses
   */
  // @Override
  // public InetAddress[] getAddress() {
  // try {
  // addresses = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
  // } catch (UnknownHostException e) {
  // e.printStackTrace();
  // addresses[0] = loopback;
  // }
  // return addresses;
  // }

  /**
   * DOCME
   * 
   * @return
   */
  // @Override
  // public InetAddress publishAddress() {
  // // TODO For each member in Herd, RMI the address to each Member
  // for (Member m : herdMembers) {
  // // send the leaders IP
  // }
  // return null;
  // }

  /**
   * When called this method will return a list of all registered members currently known to the
   * leader of the herd.
   * 
   * @return The current state of the members list
   */
  public Collection<Member> getMemebers() throws RemoteException {// TODO refactor to herd
    // potentially depreciated
    return herdMembers;
  }

  /**
   * When two leaders come into contact with each other and want to merge herds, **stuff** will
   * happen that results in an elected leader for the "new" herd as well as the transitioning of
   * current members from the "old" one.
   */
  @Override
  public void leaderDiscussMerge() throws RemoteException {// TODO send herd data so decision can be
                                                           // made?
    // TODO Auto-generated method stub
    // Will be related to herd merging
    throw new UnsupportedOperationException("method not implemented");
  }

  /**
   * DOCME
   */
  @Override
  public void updateModel(Herd newHerdData) throws RemoteException {// TODO is the input a herd DT?
    // TODO Auto-generated method stub
    // Won't be update model
  }

  @Override
  public Herd getState() throws RemoteException {
    return herd;
    // TODO Auto-generated method stub
    // bundle up all state and send it to the client
    // displayable needs current map and destination
  }

  /**
   * DOCME
   */
  @Override
  public ArrayList<RemoteMember> register(RemoteMember joiningMember) throws RemoteException {
    if (herd == null) {
      herd = new Herd(joiningMember);
    }
    if (herd.theLeader == null) {
      herd.theLeader = this;
    }
    updateModel(joiningMember.getLocalHerdData());
    joiningMember.RMITest();
    return herd.requestJoin(joiningMember);// FIXME adjust for herd
    // used to register a client for server/client/mvc
    // Likely to take a member and add them to the 'registered' list??
  }

  /**
   * DOCME
   */
  @Override
  public ArrayList<RemoteMember> deregister(RemoteMember leavingMember) throws RemoteException {
    // updateModel(herd.requestLeave(leavingMember));//FIXME check logic
    return herd.requestLeave(leavingMember);// FIXME adjust for herd
    // not sure that's right - will look into
    // As with register but removing??
  }

  /**
   * DOCME
   */
  @Override
  public boolean pathfind() throws RemoteException {// doenst take a WP ad dest should already be
                                                    // set
    // sanity check (herd has a dest and map)
    // stub for splitting work for parallel

    herd.path = herd.getProcessors().get(0).processPathLump(herd);// dumbly get first until parallel
    if (herd.path != null) {
      return true;
    }
    return false;
    // TODO Auto-generated method stub
    // An internal call to actual pathfinding
  }

  @Override
  public Boolean go() throws RemoteException {
    // if there is a dest and path //else clean up
    // TODO Auto-generated method stub
    // actual method that makes bots drive through the path calc'ed
    // for each reg'ed bot //FIXME needs some form of 'queue' so that bots can follow (or all bots
    // will go to the first WP and crash)
    for (Driveable cb : herd.getDrivers()) { // TODO RM should be drivable and in h.drivers
      while (herd.path.getLength() > 0) {// TODO GT or GT|E
        Waypoint w = herd.path.poll();
        try {
          if (!cb.drive(w).equals(w)) {
            // FIXME do something!! //ultrasound blocked
          }
          if (herd.getSensors().contains(cb)) {// FIXME unsafe cast!
            herd.map.addLayer(((LSenseable) cb).lSense());// take LiDAR Read
          }
        } catch (RemoteException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } ;
      }
    }
    // for each wp in path
    // drive that bot to that wp
    // return "it got there"
    return null;
  }

  @Override
  public boolean setDestination(Waypoint w) throws RemoteException {
    // TODO Auto-generated method stub
    herd.dest = w;
    return true;// TODO some logic
  }

  @Override
  public void RMITest() throws RemoteException {
    System.out.println("Leader RMITest was called");
  }


}
