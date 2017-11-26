/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public interface Member {
  boolean joinHerd(Herd h);//pass in herd?
  boolean leaveHerd();
  boolean becomeLeader();//soem form of UID (use object ID??)
  
}
