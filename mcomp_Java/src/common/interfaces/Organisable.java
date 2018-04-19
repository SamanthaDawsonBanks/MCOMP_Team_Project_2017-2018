package common.interfaces;


/**
 * An object is organisable if it supports the ability to hold an election and select a Leader.
 * 
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 * @version 1.0
 * @since 2018-04-07
 * 
 * @see common.interfaces.RemoteMember
 * @see common.objects.Member
 * @see common.objects.Herd
 * @see common.objects.Leader
 * @see common.interfaces.RemoteLeader
 *
 */

public interface Organisable {

  /**
   * Control method for prompting the collection(Herd) to choose (and then later promote) a Member(Promotable) to a Leader
   * 
   * @see common.datatypes.Ability
   * @see common.objects.Herd
   * @see common.objects.Leader
   *
   * @return The *Member* object that is to later *become* the Leader
   */  public Promotable electLeader();

}
