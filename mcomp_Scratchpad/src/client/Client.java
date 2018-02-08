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

  /**
   * Main entry point for the client-server connection process. Creates an instance of 
   * the serverable interface (where the stubs for methods on the server are stored) and attempts
   * to begin the connection process. 
   */
  public void start() {
    Serverable server = this.connect();
    int result = 0; //FIXME:: Only for example
    
    try {
      result = server.add(25, 5); //FIXME:: Again for example, not relevant for further implementation
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    
    System.out.println(result);
  }

  /**
   * Establishes a connection with the target server. Methods can then be invoked remotely
   * once/if the connection is successful. If this fails, an error is printed to
   * the console.
   * 
   * @return The server object
   */
  private Serverable connect() {
    Serverable server = null;

    try {
      server = (Serverable) Naming.lookup("//localhost/Server");
    } catch (Exception e) {
      //TODO a better way of handling a failed connection will be needed as the project develops
      e.printStackTrace();
    }

    return server; 
  }

}
