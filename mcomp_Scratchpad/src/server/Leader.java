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
 * An instance of a server can be created by a client and will make behaviour
 * available to it. The stubs for this (Serverable) are stored in a registry that can be 
 * referenced by clients to trigger their corresponding implementations defined below.
 * 
 */
public class Leader extends UnicastRemoteObject implements Serverable {

  private int portNumber = -1;
  private String serverName = "";
  private Registry r;
  
  //TODO Hard coded for now - a method of obtaining IP adresses will be required
  private final String IP_ADDRESS = "192.168.X.X";

  /**
   * Constructor which handles assigning the port number and server name to 
   * local values for local reference.
   * 
   * @param portNo the port number for the server
   * @param serverName the name of the server instance i.e. "newServer"
   * @throws RemoteException
   */
  public Leader(int portNo, String serverName) throws RemoteException {
    this.portNumber = portNo;
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
   * Ignore in terms of functionality. This is an example method which can be called by a Member.
   */
  @Override
  public int add(int a, int b) {
    return a + b;
  }

}
