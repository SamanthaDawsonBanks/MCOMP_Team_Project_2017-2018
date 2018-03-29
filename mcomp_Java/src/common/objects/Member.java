package common.objects;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.datatypes.Ability;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.MapLayer;
import common.datatypes.path.Path;
import common.interfaces.Bossable;
import common.interfaces.Directable;
import common.interfaces.Drawable;
import common.interfaces.Driveable;
import common.interfaces.Groupable;
import common.interfaces.LSenseable;
import common.interfaces.Notifiable;
import common.interfaces.Promotable;
import common.interfaces.Transferable;
import member.MemberMain;
import java.security.Key;

/**
 * 
 * @author Stephen Pope 15836791
 * @author Ryan Shoobert (15812407)
 * @author David Avery 15823926
 * 
 *         A test Member Class.
 *
 *         A Member must have a Public/Private Key pair. A Member must have a List of abilities that
 *         are of use to the Herd. A Member can Join or Leave a Herd at any time. A Member must be
 *         able to become the Leader of the Herd when nominated.
 * 
 */

public class Member implements LSenseable, Driveable, Drawable, Directable, Bossable, Transferable,
    Promotable, Notifiable, Groupable {
  private static final Logger LOGGER = Logger.getLogger(Member.class.getName());

  private ArrayList<Ability> abilities;
  private Herd herd;
  private Key myPublicKey;
  private Key myPrivateKey;
  private Key leaderPublicKey;

  /**
   * The Constructor for a Member.
   *
   * A new Member will come with a list of abilities.
   * 
   * These abilities are parsed by the constructor and used to initialise the controllers for those
   * abilities.
   * 
   * A member then initialises a Herd, becoming that Herds first member.
   * 
   * @param The list containing the Members abilities.
   * @return A new Member object.
   */

  public Member(Ability[] can) {
    LOGGER.log(Level.INFO, "Member Starting");
    abilities = new ArrayList<Ability>();
    LOGGER.log(Level.INFO, "Calling Herd Constructor");
    herd = new Herd(this);
    LOGGER.log(Level.INFO, "Herd Constructed");
    for (Ability a : can) {
      switch (a) {
        case PROCESSOR:
          abilities.add(a);
          break;
        case DRIVER:
          abilities.add(a);
          break;
        case SENSOR:
          abilities.add(a);
          break;
        case VIEWER:
          abilities.add(a);
          break;
        case DEST_SETTER:
          abilities.add(a);
          break;
        default:
          break;
      }
    }
  }


  /**
   * Retrieves a list of the Members abilities.
   * 
   * @return The ability list.
   */
  @Override
  public ArrayList<Ability> getAbilities() {
    return abilities;
  }

  /**
   * Sets the value of the Leaders publicKey when handed by a leader.
   * 
   * @param The Public key of a leader.
   */

  // @Override
  // public boolean importLeaderKey(Key pk) {
  // if (isValidKey(pk)) {
  // leaderPublicKey = pk;
  // return true;
  // } else
  // return false;
  // }


  /**
   * Retrieves the Public Encryption Key of the Member.
   * 
   * @return The Public Key.
   */
  public Key getPublicKey() {
    return myPublicKey; // TODO this needs to be of type Key once I work out ciphers.
  }

  /**
   * Validates that the provided Public Key given by a leader can be used to securely communicate
   * with the leader.
   * 
   * @param pk
   * @return True if key is valid, false if not.
   */
  private boolean isValidKey(Key pk) {
    // TODO RMI call leader, encrpyt String hello world to Leader, if leader returns Hello World
    // then true.
    return false;
  }

  // public void start() {
  // // TODO Auto-generated method stub
  //
  // }

  // will be part of the aftermath of a successful election
  public void startLeader() {
    try {
      // start/create RMI registry
      // Registry r = LocateRegistry.createRegistry(1111);
      // start leader process
      LOGGER.log(Level.INFO, "EXECing LeaderMain");
      ProcessBuilder builder = new ProcessBuilder("java", "-cp", ".", "leader.LeaderMain");
      builder.redirectErrorStream(true);
      Process p = builder.start();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO error for output stuff
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }


  @Override
  public void notifyOfChange() {
    // TODO Auto-generated method stub
    // model.getData()
  }


  @Override
  public Map processMapLump(Map m) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Path processPathLump(Path p) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public boolean setDestination(Waypoint w) {
    // TODO Auto-generated method stub
    // This method will have been called as part of an onclick even from the gui or from the command
    // line
    // Inform leader that the new destination is 'w'
    // return success/failure
    return false;
  }


  @Override
  public Waypoint drive(Waypoint w) {
    // TODO Auto-generated method stub
    // Assume there is already a serial connection
    // serial.send() drive command
    // pipe.send(encode("drive", w))
    // wait for response
    // while(!pipe.available())
    // return whatever given
    // return decode(pipe.read())
    return null;
  }


  @Override
  public MapLayer lSense() {
    // TODO Auto-generated method stub
    // Assume there is already a serial connection
    // serial.send() l sense command
    // pipe.send(encode("lsense", null))
    // wait for response
    // while(!pipe.available())
    // return whatever given
    // return decode(pipe.read())
    return null;
  }


  @Override
  public boolean joinHerd(Herd newHerd) {
    // TODO Auto-generated method stub
    //
    return false;
  }


  @Override
  public Herd updateLocalHerdInfo(Herd leaderHerd) {
    // TODO Auto-generated method stub
    return herd;
  }


  @Override
  public Leader becomeLeader(Herd h) {
    // TODO Auto-generated method stub
    startLeader();
    // start the leader
    // wait?
    // connect to rmi
    // wait
    // send the RMI leader the herd info
    // return the leader on the RMI link
    return null;
  }
}

