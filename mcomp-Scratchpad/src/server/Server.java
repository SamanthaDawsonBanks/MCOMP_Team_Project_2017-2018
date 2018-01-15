package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import common.interfaces.Serverable;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * 
 */
public class Server extends UnicastRemoteObject implements Serverable {

  private int portNumber = -1;
  private String serverName = "";
  private Registry r;

  public Server(int portNo, String serverName) throws RemoteException {
    this.portNumber = portNo;
    this.serverName = serverName;
  }

  public void start() {
    try {
      r = LocateRegistry.createRegistry(this.portNumber);

      r.rebind(this.serverName, new Server(this.portNumber, this.serverName));

      System.out.println(">>Server Running<<");           
    } catch (Exception e) {
      System.err.printf(">>Server failed to start: %s<<", e.getMessage());
    }   
  }

  @Override
  public int add(int a, int b) {
    return a + b;
  }

}
