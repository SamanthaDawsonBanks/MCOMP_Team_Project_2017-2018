package common.interfaces;

import common.objects.Member;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Joinable interface
 * 
 *         An object is joinable if it has the ability to accept new Members to its Herd.
 *
 */

public interface Joinable {

  public boolean acceptMember(Member m);// TODO leader info? Is this interface badly specified?

  public boolean notifyJoin();// TODO same as above, notify the leader of a join?
}
