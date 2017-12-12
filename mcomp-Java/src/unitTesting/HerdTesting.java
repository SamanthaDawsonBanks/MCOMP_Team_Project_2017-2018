package unitTesting;
import org.junit.*;
import static org.junit.Assert.*;
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
		arr [0] = "D";
		arr [1] = "P";
		Member a =  new Member(arr);
		Herd b = new Herd (a);
		assert(b.getMembers().size() > 0);
		System.out.println(b.getHerdID());
		System.out.println(b.getMembers());
	}
	
	

}
