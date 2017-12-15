package unitTesting;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import common.Herd;
import common.Member;

/**
 * 
 * @author Stephen Pope 15836791
 * 
 * jUnit testing class for the Herd Object.
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
	public void create() {
		String [] arr = new String [2];
		arr [0] = "Driver";
		arr [1] = "Processor";
		Member a =  new Member(arr);
		Herd b = new Herd (a);
		assert(b.getMembers().size() == 1);
		System.out.println(b.getHerdID());
		System.out.println(b.getMembers());
	}
	
	@Test
	/*
	 * Tests the addition of multiple regular Members 
	 */
	public void addManyMembers() {
		String [] arr = {"Driver", "Processor"};
		Member theMember = new Member(arr); 
		Herd theHerd = new Herd(theMember);
		ArrayList <Member> members = new ArrayList<Member>();
		for (int i = 0; i < 5; i++) {
			String [] abil = {"Driver", "Processor", "Sensor"};
			for (int j = 0; j < 10; j++) {
				members.add(new Member(abil));
			}
		}
		for (int i = 0; i < 5; i++) {
			String [] abil2 = {"Viewer"};
			for (int j = 0; j < 10; j++) {
				members.add(new Member(abil2));
			}
		}
		String [] abil3 = {"DestSetter"};
		members.add(new Member(abil3));
		for (Member a: members) {
			a.joinHerd(theHerd);
		}
		
		assert(theHerd.getMembers().size() == 102);
		assert(theHerd.getDrivers().size() == 51);
		assert(theHerd.getSensors().size() == 50);
		assert(theHerd.getViewers().size() == 50);
	}
	
	

}
