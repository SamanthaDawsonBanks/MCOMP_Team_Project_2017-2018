/**
 * 
 */
package common.interfaces;

import java.net.InetAddress;

/**
 * @author David Avery
 * @author Stephen Pope 15836791
 */
public interface Leadable {

  /**
   * 
   */
  //pass in first bot?? member?? self??
  //IP Address publishing added
  //Data type?? array?? array list?? queue?? (q means leader = first in / oldest member??)
  Membership nominateLeader();
  InetAddress [] getAddress();
  InetAddress publishAddress();
  
}
