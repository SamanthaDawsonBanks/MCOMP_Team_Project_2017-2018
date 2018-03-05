package common.objects;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import common.interfaces.Leadable;
import common.interfaces.Membership;
import common.interfaces.Rmiable;

public class Leader extends UnicastRemoteObject implements Leadable, Rmiable {
  private int portNumber = -1;
  private String serverName = "";
  private Registry r;

  private ArrayList <Member> herdMembers;

  InetAddress[] addresses;
  InetAddress loopback = InetAddress.getLoopbackAddress();

  /**
   * Constructor for the leader object. This will take a port number and
   * a name and store these in local variables.
   * 
   * @param portNumber The port number that the leader will communicate on
   * @param serverName The assigned name to the running instance
   *        //This may later change to the HerdID
   */
  public Leader(int portNumber, String serverName) throws RemoteException {
    this.portNumber = portNumber;
    this.serverName = serverName;
  }

  /**
   * Creates a registry for sharing method stubs with other members
   * of the herd. Once running, members will be able to call methods
   * implemented on the leader.
   */
  public void start() {
    try {
      r = LocateRegistry.createRegistry(this.portNumber);

      r.rebind(this.serverName, new Leader(this.portNumber, this.serverName));

      System.out.println(">>The leader process started succesfully<<");           
    } catch (Exception e) {
      System.err.printf(">>Leader process failed to start: %s<<", e.getMessage());
    }   
  }

  /**
   * Polls the host of the Leader and collects the IP address of all 
   * reachable interfaces. If an address cannot be reached, return 
   * the loopback address.
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

  @Override
  public Membership nominateLeader() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public InetAddress publishAddress() {
    // TODO For each member in Herd, RMI the address to each Member
    for(Member m : herdMembers) {
      //send the leaders IP
      //m.setLeaderIP()
    }
    return null;
  }
}
