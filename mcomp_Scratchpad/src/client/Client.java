package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import common.interfaces.Serverable;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * 
 * Once a successful connection the target server has been established, method stubs
 * can be called on the server resulting in the said methods being invoked and
 * a result returned (if applicable)
 * 
 */
public class Client {


  public void start() {
    Serverable server = this.connect();
    int result = 0;
    
    try {
      result = server.add(25, 5);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    
    System.out.println(result);
  }

  /**
   * Establishes a connection with the target server for RMI
   * 
   * @return The server object
   */
  private Serverable connect() {
    Serverable server = null;

    try {
      server = (Serverable) Naming.lookup("//localhost/Server");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return server; 
  }

}
