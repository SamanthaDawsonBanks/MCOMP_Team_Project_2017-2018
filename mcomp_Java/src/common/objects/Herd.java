package common.objects;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.datatypes.Ability;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;
import common.interfaces.Bossable;
import common.interfaces.Directable;
import common.interfaces.Driveable;
import common.interfaces.Joinable;
import common.interfaces.LSenseable;
import common.interfaces.Notifiable;
import common.interfaces.Organisable;
import common.interfaces.RemoteLeader;
import common.interfaces.RemoteMember;

/**
 * 
 * @author Stephen Pope 15836791
 * @author Ryan Shoobert (15812407)
 * @author David Avery 15823926
 *
 *         A test class for a Herd.
 *
 *         In order to exist, a Herd must have 1 member when it is created. Once created, a Herd
 *         must nominate a Leader. This will always result in the Founding member beginning as the
 *         leader.
 *
 *         A Herd holds lists containing all members and lists for members with certain abilities.
 * 
 */

public class Herd implements Joinable, Organisable {
  private static final Logger LOGGER = Logger.getLogger(Herd.class.getName());

  private String herdID;
  protected RemoteLeader theLeader;
  private ArrayList<RemoteMember> herdMembers;
  private ArrayList<Driveable> herdDrivers;
  private ArrayList<LSenseable> herdSensors;
  private ArrayList<Bossable> herdProcessors;
  private ArrayList<Notifiable> herdViewers;
  private Directable herdDestSetter;

  protected Map map;
  protected Waypoint dest;
  protected Path path;

  /**
   * The Herd constructor.
   * 
   * A new Herd is handed its founding member. When storage has been allocated, the founding member
   * is queried for its abilities. It will then populate the appropriate lists. An election is then
   * called.
   * 
   * @param A Member to be the founder of the Herd.
   * @return A new Herd object.
   * @throws RemoteException
   */
  public Herd(RemoteMember a) {
    LOGGER.log(Level.INFO, "Herd Starting");

    // Storage Initialisation
    herdID = "newHerd"; // TODO Needs to be a randomly generated name
    herdMembers = new ArrayList<RemoteMember>();
    herdDrivers = new ArrayList<Driveable>();
    herdSensors = new ArrayList<LSenseable>();
    herdProcessors = new ArrayList<Bossable>();
    herdViewers = new ArrayList<Notifiable>();

    requestJoin(a);

    try {
      theLeader = electLeader().becomeLeader(this);// on that robot, start the leader process
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * The nomination process. The Leader variable is populated by the Member Object of the Leader.
   * The election process picks the oldest member in the Herd.
   * 
   * @return The leader of the Herd.
   */
  @Override
  public RemoteMember electLeader() {
    LOGGER.log(Level.INFO, "Choosing Leader");
    return herdMembers.get(0);// TODO get oldist from all or subtype?
  }

  /*
   * This is where requests to join the Herd are handled. In the full implementation, a Herd can
   * only be "merged" with another, so this method will either change signature or be deprecated.
   * 
   */

  /**
   * A member requests to join the Herd by calling this method. The Herd will request a Public Key
   * and Abilities List. If the Public Key is a valid Public Key then the Herd will respond with the
   * herdMembers List.
   * 
   * @param The Member object requesting to join the Herd.
   * @return The list of all current members in the Herd.
   */
  @Override
  public ArrayList<RemoteMember> requestJoin(RemoteMember aspiringMember) {
    herdMembers.add(aspiringMember);
    try {
      for (Ability a : aspiringMember.getAbilities()) {
        switch (a) {
          case DRIVER:
            herdDrivers.add(aspiringMember);
            break;
          case PROCESSOR:
            herdProcessors.add(aspiringMember);
            break;
          case SENSOR:
            herdSensors.add(aspiringMember);
            break;
          case VIEWER:
            herdViewers.add(aspiringMember);
            break;
          case DEST_SETTER:
            if (herdDestSetter == null) { // first wins
              herdDestSetter = aspiringMember;
            }
            break;
        }
      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return herdMembers;
  }

  /**
   * This where requests to leave the Herd are handled. In order to leave, a member must be removed
   * from the Herds lists of specialists. All remaining members must be notified of the new state of
   * the Herd. If the leaving member is a Leader, then an election must be held.
   * 
   * @param The member object leaving the Herd.
   * @return The list of remaining members.
   */
  @Override
  public ArrayList<RemoteMember> requestLeave(RemoteMember leavingMember) {
    /*
     * TODO Consider the return type. If a member leaves, do they need returned the state of the
     * herd? Perhaps this should be a void, or return an enum.
     */
    /*
     * TODO Implement a test for leave. If a member is the last Herd member, should the Herd be
     * destroyed? What happens to it's discoveries about the surrounding world?
     */
    herdMembers.remove(leavingMember);
    herdDrivers.remove(leavingMember);
    herdProcessors.remove(leavingMember);
    herdSensors.remove(leavingMember);
    herdViewers.remove(leavingMember);
    if (herdDestSetter.equals(leavingMember)) {
      herdDestSetter = null;// TODO then check for secondary dest setters?
    }
    return herdMembers;
  }

  /**
   * Retrieves the unique ID of the Herd.
   * 
   * @return The herd ID.
   */
  @Override
  public String getHerdID() {
    return herdID;
  }

  /**
   * Retrieve the list of all Members of the Herd.
   * 
   * @return The list of Herd Members.
   */
  @Override
  public ArrayList<RemoteMember> getMembers() {
    return herdMembers;
  }

  /**
   * Retrieve a single member from the List
   * 
   * @param The Public Key of a member in the Herd.
   * @return The Member object if it exists, null if not.
   */
  @Override
  public RemoteMember getMember(String theKey) {//TODO remove key
    for (RemoteMember a : herdMembers) {
      try {
        if (a.getPublicKey().equals(theKey)) {
          return a;
        }
      } catch (RemoteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Retrieve a list of all the Herds Drive-Capable Members.
   * 
   * @return The list of Drivers.
   */
  @Override
  public ArrayList<Driveable> getDrivers() {
    return herdDrivers;
  }

  /**
   * Retrieve a list of all the Herds Map-Processing-Capable Members.
   * 
   * @return The list of Processors.
   */
  @Override
  public ArrayList<Bossable> getProcessors() {
    return herdProcessors;
  }

  /**
   * Retrieve a list of all the Herds Sensor-Equipped Members.
   * 
   * @return The list of Sensors.
   */
  @Override
  public ArrayList<LSenseable> getSensors() {
    return herdSensors;
  }

  /**
   * Retrieve a list of all the Herds GUI-Capable Members.
   * 
   * @return The list of Viewers.
   */
  @Override
  public ArrayList<Notifiable> getViewers() {
    return herdViewers;
  }

  /**
   * Retrieve the Member of the Herd responsible for setting destinations.
   * 
   * @return The Destination Setter.
   */
  @Override
  public Directable getDestSetter() {
    return herdDestSetter;
  }

  @Override
  public boolean acceptMember(Member m) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean notifyJoin() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeMember(Member m) {
    // TODO Auto-generated method stub
    return false;
  }

  public Map getMap() {
    // TODO Auto-generated method stub
    return map;
  }

  public Path getPath() {
    // TODO Auto-generated method stub
    return path;
  }

  public ArrayList<Vertex> getUnoptimizedPath() {
    // TODO Auto-generated method stub
    return null;
  }

  public ArrayList<Vertex> getSearchedNodes() {
    // TODO Auto-generated method stub
    return null;
  }

  public ArrayList<Vertex> getOptimizedPath() {
    // TODO Auto-generated method stub
    return null;
  }


}
