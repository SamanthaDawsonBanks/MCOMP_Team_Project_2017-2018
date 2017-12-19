package common;

import java.rmi.Naming;

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
    Server server = this.connect();

    int result = server.add(1, 45);
    System.out.println(result);
  }

  /**
   * Establishes a connection with the target server for RMI
   * 
   * @return The server object
   */
  private Server connect() {
    Server server = null;

    try {
      server = (common.Server) Naming.lookup("//localhost/Server");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return server; 
  }

}
