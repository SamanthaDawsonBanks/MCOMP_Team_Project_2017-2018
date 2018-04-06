package unitTesting;

import org.junit.Before;
import org.junit.Test;
import java.rmi.RemoteException;
import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Herd;
import common.objects.Member;

/**
 * 
 * @author Stephen Pope 15836791
 * @author Ryan Shoobert (15812407)
 * 
 *         jUnit testing class for the Herd Object.
 *
 */
public class HerdTesting {
  @Before
  public void setUp() {

  }

  /*
   * Tests the creation of a basic herd with one member added to it.
   */
  @Test
  public void create() throws RemoteException {
    Ability[] arr = new Ability[2];
    arr[0] = Ability.DRIVER;
    arr[1] = Ability.DEST_SETTER;
    Member a = new Member(arr);
    Herd b = new Herd(a);
    assert (b.getMembers().size() == 1);
    System.out.println(b.getHerdID());
    System.out.println(b.getMembers());
  }

  @Test
  /*
   * Tests the addition of multiple regular Members
   */
  public void addManyMembers() throws RemoteException {
    Ability[] arr = {Ability.DRIVER, Ability.DEST_SETTER};
    Member theMember = new Member(arr);
    Herd theHerd = new Herd(theMember);
    ArrayList<Member> members = new ArrayList<Member>();
    for (int i = 0; i < 5; i++) {
      Ability[] abil = {Ability.DRIVER, Ability.PROCESSOR, Ability.SENSOR};
      for (int j = 0; j < 10; j++) {
        members.add(new Member(abil));
      }
    }
    for (int i = 0; i < 5; i++) {
      Ability[] abil2 = {Ability.VIEWER};
      for (int j = 0; j < 10; j++) {
        members.add(new Member(abil2));
      }
    }
    Ability[] abil3 = {Ability.DEST_SETTER};
    members.add(new Member(abil3));
    members.add(new Member(abil3));
    for (Member a : members) {
      // FIXME
      // a.joinHerd(theHerd);
    }

    assert (theHerd.getMembers().size() == 102);
    assert (theHerd.getDrivers().size() == 51);
    assert (theHerd.getSensors().size() == 50);
    assert (theHerd.getViewers().size() == 50);
  }



}
