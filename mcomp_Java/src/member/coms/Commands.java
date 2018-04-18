/**
 * 
 */
package member.coms;

/**
 * A member can issue commands and use data returned as a result of those commands. This list
 * defines all of the current commands that can be issued.
 * 
 * @author Ryan Shoobert 15812407
 * 
 * @version 1.0
 * @since 2018-04-16
 *
 */
public enum Commands {
  // NOTE: although defined, there is currently no handling of the STOP command
  COMPASS, DRIVE, L_SENSE, STOP
}
