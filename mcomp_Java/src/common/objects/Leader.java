package common.objects;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
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
import common.interfaces.RemoteView;
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

  /**
   * 
   */
  private static final long serialVersionUID = 9095319900672719682L;

  private static final Logger LOGGER = Logger.getLogger(LeaderMain.class.getName());

  private Herd leaderHerd;


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
  public Leader() throws RemoteException {
    // FIXME needs initialisation loop from member process via RMI?
    // make herd etc

  }

  /**
   * When called this method will return a list of all registered members currently known to the
   * leader of the leaderHerd.
   * 
   * @return The current state of the members list
   */
  @Override
  public ArrayList<RemoteMember> getMemebers() throws RemoteException {// TODO refactor to
                                                                       // leaderHerd
    // potentially depreciated
    return leaderHerd.getMembers();
  }

  /**
   * When two leaders come into contact with each other and want to merge herds, **stuff** will
   * happen that results in an elected leader for the "new" leaderHerd as well as the transitioning
   * of current members from the "old" one.
   */
  @Override
  public boolean leaderDiscussMerge(Herd h) throws RemoteException {// TODO send leaderHerd data so
                                                                    // decision can be
    // made?
    // TODO Auto-generated method stub
    // Will be related to leaderHerd merging
    throw new UnsupportedOperationException("method not implemented");
    // return true;
  }

  @Override
  public Herd getState() throws RemoteException {
    return leaderHerd;
    // TODO Auto-generated method stub
    // bundle up all state and send it to the client
    // displayable needs current map and destination
  }

  /**
   * DOCME
   */
  @Override
  public ArrayList<RemoteMember> register(RemoteMember joiningMember) throws RemoteException {
    if (leaderHerd == null) {
      leaderHerd = new Herd(joiningMember);
    }
    if (leaderHerd.getTheLeader() == null) {
      leaderHerd.setLeader(this);
    }
    // TODO some form of notify all??
    // updateModel(joiningMember.getLocalHerdData()); //FIXME do we need this??
    joiningMember.RMITest();// FIXME this checks loopback

    joiningMember.notifyOfChange();
    
    if (leaderHerd.getSensors().contains(joiningMember)) {
      leaderHerd.addMapLayer(joiningMember.lSense());// take LiDAR Read
    }
    
    // TODO do something ? take read ? dance?!?!?!
    return leaderHerd.requestJoin(joiningMember);
    // FIXME adjust for leaderHerd
    // used to register a client for server/client/mvc
    // Likely to take a member and add them to the 'registered' list??
  }

  @Override
  public ArrayList<RemoteView> register(RemoteView joiningView) throws RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * DOCME
   */
  @Override
  public ArrayList<RemoteMember> deregister(RemoteMember leavingMember) throws RemoteException {
    // updateModel(leaderHerd.requestLeave(leavingMember));//FIXME check logic
    return leaderHerd.requestLeave(leavingMember);// FIXME adjust for leaderHerd
    // not sure that's right - will look into
    // As with register but removing??
  }

  /**
   * DOCME
   */
  @Override
  public ArrayList<RemoteView> deregister(RemoteView leavingView) throws RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * DOCME
   */
  @Override
  public boolean pathfind() throws RemoteException {

    leaderHerd.unoptimizedPath = leaderHerd.getProcessors().get(0).processPathLump();
    // dumbly get first until parallel
    return (leaderHerd.unoptimizedPath != null);
  }

  @Override
  public boolean optimizePath() throws RemoteException {
    leaderHerd.optimizedPath = leaderHerd.getProcessors().get(0).optimizePathLump();
    // dumbly get first until parallel
    return (leaderHerd.optimizedPath != null);
  }

  @Override
  public Boolean go() throws RemoteException {// FIXME called by the GUI/cont?
    // if there is a dest and path //else clean up
    boolean res = true; // assume ok
    if (leaderHerd.dest == null) {
      // some form of error?
      res = false;
    }
    if (leaderHerd.unoptimizedPath == null) {
      if (!pathfind()) {
        res = false;
      }
    }
    if (leaderHerd.optimizedPath == null) {
      if (!optimizePath()) {
        res = false;
      }
    }

    // actual method that makes bots drive through the path calc'ed
    // for each reg'ed bot //FIXME needs some form of 'queue' so that bots can follow (or all bots
    // will go to the first WP and crash)
    for (Driveable cb : leaderHerd.getDrivers()) { // TODO RM should be drivable and in h.drivers
      while (leaderHerd.optimizedPath.getLength() > 0) {// TODO GT or GT|E
        Waypoint w = leaderHerd.optimizedPath.poll();
        try {
          if (!cb.drive(w).equals(w)) {
            // FIXME do something!! //ultrasound blocked
          }
          if (leaderHerd.getSensors().contains(cb)) {// FIXME unsafe cast!
            leaderHerd.addMapLayer(((LSenseable) cb).lSense());// take LiDAR Read
          }
        } catch (RemoteException e) {
          //deregister member
          deregister((RemoteMember) cb);
          e.printStackTrace();
        } ;
      }
    }
    // for each wp in path
    // drive that bot to that wp
    // return "it got there"
    return res;
  }

  @Override
  public boolean setDestination(Waypoint w) throws RemoteException {
    leaderHerd.dest = w;
    return true;// TODO some logic
  }


  @Override
  public boolean kill(String log) throws RemoteException {
    LOGGER.log(Level.SEVERE, log);
    LeaderMain.stayingAlive = false;
    return true;// TODO some logic
  }


  @Override
  public void RMITest() throws RemoteException {
    System.out.println("Leader RMITest was called");
  }

}
