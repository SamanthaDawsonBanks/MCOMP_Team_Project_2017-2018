package unitTesting;
import org.junit.*;
import static org.junit.Assert.*;

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
	 * Need to change the type of Array that passes values to 
	 */
	public void addManyMembers() {
		String [] arr = {"Driver", "Processor"};
		Member theMember = new Member(arr); 
		Herd theHerd = new Herd(theMember);
		ArrayList <Member> members = new ArrayList<Member>();
		for (int i = 0; i < 5; i++) {
			String [] abil = {"Driver", "Processor", "Sensor"};
			//TODO Fix the below function to produce various abilities for a Member
/*			switch (i) {
			case 0:
				abil [0] = "D";
				break;
			case 1:
				abil[0] = "D";
				abil[1] = "P";
				abil[2] = "S";
				break;
			case 2:
				abil[0] = "V";
				break;
			case 3:
				abil[0] = "V";
				abil[1] = "W";
				break;
			case 4:
				abil[0] = "D";
				abil[1] = "P";
				abil[2] = "S";
				abil[3] = "V";
				abil[4] = "W";
				break;
			}*/
			for (int j = 0; j < 10; j++) {
				members.add(new Member(abil));
			}
		}
		for (Member a: members) {
			a.joinHerd(theHerd);
		}
		System.out.println(theHerd.getMembers().size());
		assert(theHerd.getMembers().size() == 51);
	}
	
	

}
