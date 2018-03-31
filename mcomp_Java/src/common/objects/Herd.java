package common.objects;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.datatypes.Ability;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.path.Path;
import common.interfaces.Bossable;
import common.interfaces.Directable;
import common.interfaces.Driveable;
import common.interfaces.Joinable;
import common.interfaces.LSenseable;
import common.interfaces.Notifiable;
import common.interfaces.Organisable;
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
  private RemoteMember theLeader;
  private ArrayList<Member> herdMembers;
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
   */
  public Herd(Member a) {
    LOGGER.log(Level.INFO, "Herd Starting");

    // Storage Initialisation
    herdID = "newHerd"; // TODO Needs to be a randomly generated name
    herdMembers = new ArrayList<Member>();
    herdDrivers = new ArrayList<Driveable>();
    herdSensors = new ArrayList<LSenseable>();
    herdProcessors = new ArrayList<Bossable>();
    herdViewers = new ArrayList<Notifiable>();

    // Ability Querying
    herdMembers.add(a);
    for (Ability b : a.getAbilities()) {
      switch (b) {
        case DRIVER:
          herdDrivers.add(a);
          break;
        case PROCESSOR:
          herdProcessors.add(a);
          break;
        case SENSOR:
          herdSensors.add(a);
          break;
        case VIEWER:
          herdViewers.add(a);
          break;
        case DEST_SETTER:
          herdDestSetter = a;
          break;
      }
    }

    // startup election
    theLeader = electLeader();

    // on that robot, start start the leader process
    theLeader.becomeLeader(this);
  }

  /**
   * The nomination process. The Leader variable is populated by the Member Object of the Leader.
   * The election process picks the oldest member in the Herd.
   * 
   * @return The leader of the Herd.
   */
  @Override
  public RemoteMember electLeader() {
    // TODO Auto-generated method stub
    LOGGER.log(Level.INFO, "Choosing Leader");
    theLeader = herdMembers.get(0);// TODO get oldist from all or subtype?
    return theLeader;
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
  public ArrayList<Member> requestJoin(Member aspiringMember) {
    // TODO Find a test to validate the Key of a Member/Herd
    herdMembers.add(aspiringMember);
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
  public ArrayList<Member> requestLeave(Member leavingMember) {
    /*
     * TODO Consider the return type. If a member leaves, do they need returned the state of the
     * herd? Perhaps this should be a void, or return an enum.
     */
    /*
     * TODO Implement a test for leave. If a member is the last Herd member, should the Herd be
     * destroyed? What happens to it's discoveries about the surrounding world?
     */
    herdMembers.remove(leavingMember);
    for (Ability b : leavingMember.getAbilities()) {
      switch (b) {
        case DRIVER:
          herdDrivers.remove(leavingMember);
          break;
        case PROCESSOR:
          herdProcessors.add(leavingMember);
          break;
        case SENSOR:
          herdSensors.add(leavingMember);
          break;
        case VIEWER:
          herdViewers.add(leavingMember);
          break;
        case DEST_SETTER:
          herdDestSetter = null;
          break;
      }
    }
    return herdMembers;
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
  public String getHerdID() {
    return herdID;
  }

  /**
   * Retrieve the list of all Members of the Herd.
   * 
   * @return The list of Herd Members.
   */
  public ArrayList<Member> getMembers() {
    return herdMembers;
  }

  /**
   * Retrieve a single member from the List
   * 
   * @param The Public Key of a member in the Herd.
   * @return The Member object if it exists, null if not.
   */
  public Member getMember(String theKey) {
    for (Member a : herdMembers) {
      if (a.getPublicKey().equals(theKey)) {
        return a;
      }
    }
    return null;
  }

  /**
   * Retrieve a list of all the Herds Drive-Capable Members.
   * 
   * @return The list of Drivers.
   */
  public ArrayList<Driveable> getDrivers() {
    return herdDrivers;
  }

  /**
   * Retrieve a list of all the Herds Map-Processing-Capable Members.
   * 
   * @return The list of Processors.
   */
  public ArrayList<Bossable> getProcessors() {
    return herdProcessors;
  }

  /**
   * Retrieve a list of all the Herds Sensor-Equipped Members.
   * 
   * @return The list of Sensors.
   */
  public ArrayList<LSenseable> getSensors() {
    return herdSensors;
  }

  /**
   * Retrieve a list of all the Herds GUI-Capable Members.
   * 
   * @return The list of Viewers.
   */
  public ArrayList<Notifiable> getViewers() {
    return herdViewers;
  }

  /**
   * Retrieve the Member of the Herd responsible for setting destinations.
   * 
   * @return The Destination Setter.
   */
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
}
