/**
 * 
 */
package member;

import common.objects.Herd;
import common.objects.Member;
import java.util.ArrayList;
import common.datatypes.Ability;

/**
 * @author Stephen Pope 15836791
 * @author Ryan Shoobert (15812407)
 * 
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
 * The entry point for a client to be created and used to
 * connect to a running server and invoke methods on it.
 * 
 * @param The abilities of the member as a String
 */
public class MemberMain {

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
      System.out.println("Sorry, a Robot needs to have at least one ability!");
    }
    else {
      Ability[] abilities = new Ability[skills.size()];
      
      for(int i = 0; i < skills.size(); i++) {
        abilities[i] = (Ability) skills.get(i);
      }

      Member me = new Member(abilities);
      me.start();
      System.out.println("Member started");
      Herd h = new Herd (me); //Here because Herd needs a constructed Member to add.
    }
  }

}