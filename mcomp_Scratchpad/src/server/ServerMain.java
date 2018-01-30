package server;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * 
 * Main entry point for starting an instance of the server. To create this, a port number
 * and a name for the instance must be parsed in before the start method can be called
 * to set up a registry and bind behaviour for clients to access.
 * 
 */
public class ServerMain {
  public static void main(String[] args) throws Exception {
    new Server(1099, "Server").start();
  }
}
