package common.objects;

import java.io.Serializable;
import java.rmi.Remote;
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
import common.interfaces.Promotable;
import common.interfaces.RemoteLeader;
import common.interfaces.RemoteMember;
import common.interfaces.RemoteView;

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

public class Herd implements Serializable, Joinable, Organisable {
  /**
   * 
   */
  private static final long serialVersionUID = 3973559806177088667L;

  private static final Logger LOGGER = Logger.getLogger(Herd.class.getName());

  private String herdID;

  private RemoteLeader theLeader;

  private ArrayList<RemoteMember> herdMembers;
  private ArrayList<Driveable> herdDrivers;
  private ArrayList<LSenseable> herdSensors;
  private ArrayList<Bossable> herdProcessors;
  private ArrayList<Notifiable> herdViewers;
  private ArrayList<RemoteView> herdViews;
  private Directable herdDestSetter;

  protected Map map;
  protected Waypoint dest;

  protected Path unoptimizedPath;
  protected ArrayList<Vertex> searchedNodes;
  protected Path optimizedPath;


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

    herdViews = new ArrayList<RemoteView>();

    requestJoin(a);

    try {
      electLeader().becomeLeader(this);// on that robot, start the leader process
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
  public Promotable electLeader() {
    // TODO Auto-generated method stub
    LOGGER.log(Level.INFO, "Choosing Leader");
    return (Promotable) herdProcessors.get(0);// FIXME fix after interface squash
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
    // TODO Find a test to validate the Key of a Member/Herd
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
            // TODO Need code to check the old destSetter and remove it if it only has the one
            // ability.
            if (herdDestSetter == null) { // first wins
              // if (herdDestSetter.getAbilities().size() > 1) {
              // herdDestSetter.getAbilities().remove(Ability.DEST_SETTER);
              // } else {
              // // No longer a leave method
              // // herdDestSetter.leaveHerd(this);
              // }
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

  @Override
  public ArrayList<RemoteView> requestJoin(RemoteView aspiringView) {
    // TODO Find a test to validate the Key of a Member/Herd
    herdViews.add(aspiringView);
    return herdViews;
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
    try {
      for (Ability b : leavingMember.getAbilities()) {
        switch (b) {// TODO do we need the switch or just call remove with it
          case DRIVER:
            herdDrivers.remove(leavingMember);
            break;
          case PROCESSOR:
            herdProcessors.remove(leavingMember);
            break;
          case SENSOR:
            herdSensors.remove(leavingMember);
            break;
          case VIEWER:
            herdViewers.remove(leavingMember);
            break;
          case DEST_SETTER:
            if (herdDestSetter.equals(leavingMember))
              ;
            herdDestSetter = null;// TODO then check for secondary dest setters?
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
  public ArrayList<RemoteView> requestLeave(RemoteView leavingView) {
    /*
     * TODO Consider the return type. If a member leaves, do they need returned the state of the
     * herd? Perhaps this should be a void, or return an enum.
     */
    /*
     * TODO Implement a test for leave. If a member is the last Herd member, should the Herd be
     * destroyed? What happens to it's discoveries about the surrounding world?
     */
    herdViews.remove(leavingView);
    return herdViews;
  }

  // TODO Finish this
  // public ArrayList<String> publishMembers() {
  // for(String a: herdMembers){
  // //TODO for each member in the herdMembers list publish the new list to them
  // }
  // }

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
   * @return the theLeader
   */
  public RemoteLeader getTheLeader() {
    return theLeader;
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
   * Retrieve a list of all the Herds GUI-Capable Members.
   * 
   * @return The list of Viewers.
   */
  @Override
  public ArrayList<RemoteView> getViews() {
    return herdViews;
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

  protected void setLeader(RemoteLeader leader) {// only used for RMIConnect
    theLeader = leader;
  }

  public RemoteLeader getLeader() {
    return theLeader;
  }


}
