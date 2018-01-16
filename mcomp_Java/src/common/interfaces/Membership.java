/**
 * 
 */
package common.interfaces;

/**
 * @author David Avery
 *
 */
public interface Membership {
  boolean joinHerd(Leadable h);
  boolean leaveHerd();
  boolean becomeLeader();
  
}