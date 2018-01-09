package common;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * 
 */
public class ServerMain {
  public static void main(String[] args) throws Exception {
    new Server(1099, "Server").start();
  }
}
