package common.objects;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import common.interfaces.Leadable;
import common.interfaces.Membership;
import common.interfaces.Rmiable;
import leader.Leader;

public class Leader extends UnicastRemoteObject implements Leadable, Rmiable {
  private int portNumber = -1;
  private String serverName = "";
  private Registry r;

  InetAddress[] addresses;
  InetAddress loopback = InetAddress.getLoopbackAddress();

  public Leader(int portNumber, String serverName) {
    this.portNumber = portNumber;
    this.serverName = serverName;
  }
  
  /**
   * Creates registry for sharing method stubs on the defined port. This then
   * gets bound to the new instance to be created 
   */
  public void start() {
    try {
      r = LocateRegistry.createRegistry(this.portNumber);

      r.rebind(this.serverName, new Leader(this.portNumber, this.serverName));

      System.out.println(">>Server Running<<");           
    } catch (Exception e) {
      System.err.printf(">>Server failed to start: %s<<", e.getMessage());
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
  public InetAddress publishAddress() {
    // TODO For each member in Herd, RMI the address to each Member
    return null;
  }

  @Override
  public Membership nominateLeader() {
    // TODO Auto-generated method stub
    return null;
  }
  
  @Override
  public int add(int a, int b) throws RemoteException {
    // TODO Auto-generated method stub
    return 0;
  }

}
