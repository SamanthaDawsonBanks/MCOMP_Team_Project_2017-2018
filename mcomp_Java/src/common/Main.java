package common;

import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Member;

/**
 * @author Stephen Pope 15836791
 * 
 */
public class Main {

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

