/**
 * 
 */
package member;

import common.objects.Member;

/**
 * 
 * @author Ryan Shoobert (15812407)
 * 
 * The entry point for a client to be created and used to
 * connect to a running server and invoke methods on it.
 * 
 */
public class MemberMain {

  public static void main(String[] args) {
    new Member().start();
  }

}
