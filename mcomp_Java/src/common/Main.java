package common;

import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Member;

/**
 * @author Stephen Pope 15836791
 * 
 */
public class Main {
	
	/**
	 * The Main Class.
	 * 
	 * Every instance of state in a Herd begins with a member being started.
	 * 
	 * Members have all kinds of abilities that contribute to the Herd, but all are contained
	 * by a new member. The abilities are fed into the system for testing purposes (can ignore
	 * an ability on a robot for testing)
	 * 
	 * Main filters the input from the arguments supplied and creates a new member with those
	 * abilities. The member takes things from there (new Herd etc.)
	 * 
	 * @param The abilities of the member as a String
	 */
	public static void main (String [] args) {
		ArrayList<Ability> skills = new ArrayList<Ability>();
		for(String a : args) {
			switch (a) {
			case "PROCESSOR":
				skills.add(common.datatypes.Ability.PROCESSOR);
				break;
			case "DRIVER":
				skills.add(common.datatypes.Ability.DRIVER);
				break;
			case "SENSOR":
				skills.add(common.datatypes.Ability.SENSOR);
				break;
			case "VIEWER":
				skills.add(common.datatypes.Ability.VIEWER);
				break;
			case "DEST_SETTER":
				skills.add(common.datatypes.Ability.DEST_SETTER);
				break;
			default: break;
			}
		}
		if (skills.isEmpty()) {
			System.out.println("Sorry, a Pathfinder needs to have at least one ability!");
		}
		else {
			Ability [] abilities = (Ability[]) skills.toArray();  //FIXME list is already of type ability?
			Member me = new Member(abilities);
			System.out.println("Member started");
		}
	}
}

