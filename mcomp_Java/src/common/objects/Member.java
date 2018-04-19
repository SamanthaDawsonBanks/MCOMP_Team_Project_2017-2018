package common.objects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
import common.interfaces.RemoteLeader;
import common.interfaces.RemoteMember;
import common.interfaces.Transferable;
import member.MemberMain;
import member.coms.Pipe;
import pathfinding.AStar;
import unitTesting.testData.TestData;

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

public class Member extends UnicastRemoteObject implements RemoteMember, LSenseable, Driveable,
    Drawable, Directable, Bossable, Transferable, Promotable, Notifiable, Groupable {
  /**
   * 
   */
  private static final long serialVersionUID = 8834142885550920107L;

  private static final Logger LOGGER = Logger.getLogger(Member.class.getName());

  private ArrayList<Ability> abilities;
  private Herd localHerd;

  private Pipe pipe = new Pipe("COM5"); // need to know for certain which COM port the arduino will
                                        // be on

  private double currentX;
  private double currentY;

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

  public Member(Ability[] can) throws RemoteException {
    LOGGER.log(Level.INFO, "Member Starting");
    abilities = new ArrayList<Ability>();
    for (Ability a : can) {
      switch (a) {
        case PROCESSOR:
          abilities.add(a);
          break;
        case DRIVER:
          abilities.add(a);
          // FIXME some form of get location?
          this.currentX = 0.0;
          this.currentY = 0.0;
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
    LOGGER.log(Level.INFO, "Calling Herd Constructor");
    localHerd = new Herd(this);
    LOGGER.log(Level.INFO, "Herd Constructed");


    // connect to rmi
    localHerd.setLeader(connectRMI());

    if (abilities.contains(Ability.VIEWER)) {
      startGUI();
    }
  }


  private void startGUI() {
    // TODO identical to start leader - factor out? startProc(leader/gui)???
    try {
      // build and start GUI process
      LOGGER.log(Level.INFO, "EXECing GUIMain");
      ProcessBuilder GUIMainPB = new ProcessBuilder("java", "-cp", "./bin/", "common.objects.View");
      GUIMainPB.redirectErrorStream(true);
      @SuppressWarnings("unused")
      Process GUIMainP = GUIMainPB.start();

      // BufferedReader br = new BufferedReader(new InputStreamReader(GUIMainP.getInputStream()));
      // String output = "";
      // String line;
      // output = output + "\n >>>>>> BEGIN GUIMain process output <<<<<< \n\n";
      // while ((line = br.readLine()) != null) {// FIXME this loop will need to be threaded (if we
      // // keep it) to fix the blocking nature
      // output = output + line + "\n";
      // if (line.equals("INFO: End of GUIMain")) {// line is never null in this context
      // break;
      // }
      // }
      // output = output + "\n >>>>>> END GUIMain process output <<<<<<" + "\n";
      // LOGGER.log(Level.INFO, output);

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


  /**
   * Retrieves a list of the Members abilities.
   * 
   * @return The ability list.
   */
  @Override
  public ArrayList<Ability> getAbilities() throws RemoteException {
    return abilities;
  }


  // will be part of the aftermath of a successful election
  private void startLeader() {
    try {
      // build and start leader process
      LOGGER.log(Level.INFO, "EXECing LeaderMain");
      ProcessBuilder leaderMainPB =
          new ProcessBuilder("java", "-cp", "./bin/", "leader.LeaderMain");
      leaderMainPB.redirectErrorStream(true);
      @SuppressWarnings("unused")
      Process leaderMainP = leaderMainPB.start();

      // BufferedReader br = new BufferedReader(new
      // InputStreamReader(leaderMainP.getInputStream()));
      // String output = "";
      // String line;
      // output = output + "\n >>>>>> BEGIN LeaderMain process output <<<<<< \n\n";
      // while ((line = br.readLine()) != null) {// FIXME this loop will need to be threaded (if we
      // // keep it) to fix the blocking nature
      // output = output + line + "\n";
      // if (line.equals("INFO: End of LeaderMain")) {// line is never null in this context
      // break;
      // }
      // }
      // output = output + "\n >>>>>> END LeaderMain process output <<<<<<" + "\n";
      // LOGGER.log(Level.INFO, output);

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
  public void notifyOfChange() throws RemoteException {
    try {
      localHerd = localHerd.getLeader().getState();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // TODO and do something with it (probably paint it)
  }


  @Override
  public Map processMapLump() throws RemoteException {
    // TODO Auto-generated method stub

    // FIXME some form of call to map. almag layer
    return null;
  }

  @Override
  public Path processPathLump() throws RemoteException {
    Driveable robot = localHerd.getDrivers().get(0);// TODO should be specific bot
    Waypoint start = new Waypoint(robot.getPos().getX(), robot.getPos().getY());
    return new AStar().pathfind(start, localHerd.dest, localHerd.map);
  }

  @Override
  public Path optimizePathLump() throws RemoteException {
    // TODO Auto-generated method stub
    // FIXME some call to optimise path??
    return null;
  }

  @Override
  public boolean setDestination(Waypoint w) throws RemoteException {
    // TODO Auto-generated method stub
    // This method will have been called as part of an onclick even from the gui or from the command
    // line
    // Inform leader that the new destination is 'w'
    try {
      localHerd.getLeader().setDestination(w);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // return success/failure
    return true;// TODO some logic
  }

  @Override
  public Waypoint getPos() throws RemoteException {
    return new Waypoint(this.currentX, this.currentY);
  }

  @Override
  public Waypoint drive(Waypoint w) throws RemoteException {
    Waypoint res = pipe.drive(new Waypoint((w.getX() - currentX), (w.getY() - currentY)));

    // update this.x and this.y
    currentX = currentX + res.getX();
    currentY = currentY + res.getY();
    return res;
  }


  @Override
  public MapLayer lSense() throws RemoteException {
    MapLayer r = pipe.lSense();

    r = r.transform(0, -currentX, -currentY, 0.2); 

    return r;
  }

  @Override
  public boolean becomeLeader(Herd h) throws RemoteException {
    // TODO Auto-generated method stub
    LOGGER.log(Level.INFO, "Becoming Leader");

    // change WiFi mode (ready to become leader)

    // exec the Process
    startLeader();
    // start the leader

    // store the herd ready for hand off??
    return true;
  }


  private RemoteLeader connectRMI() {
    RemoteLeader res = null;
    try {
      res = (RemoteLeader) Naming.lookup("rmi://192.168.25.42" + "/HerdLeader");
      // FIXME lookup IP
      res.register(this);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return res;
  }

  @Override
  public boolean kill(String log) throws RemoteException {
    LOGGER.log(Level.SEVERE, log);
    MemberMain.stayingAlive = false;
    return true;// TODO some logic
  }


  @Override
  public void RMITest() {
    System.out.println("Member RMITest was called in the Member");
    // try {
    // localLeaderRef.RMITest();
    // } catch (RemoteException e) {
    // e.printStackTrace();
    // }
  }

}

