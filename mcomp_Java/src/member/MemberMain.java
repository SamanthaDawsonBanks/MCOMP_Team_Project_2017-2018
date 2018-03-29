/**
 * 
 */
package member;

import common.objects.Herd;
import common.objects.Member;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.datatypes.Ability;

/**
 * @author Stephen Pope 15836791
 * @author Ryan Shoobert (15812407)
 * @author David Avery 15823926
 * 
 *         The Main Class.
 * 
 *         Every instance of state in a Herd begins with a member being started.
 * 
 *         Members have all kinds of abilities that contribute to the Herd, but all are contained by
 *         a new member. The abilities are fed into the system for testing purposes (can ignore an
 *         ability on a robot for testing)
 * 
 *         Main filters the input from the arguments supplied and creates a new member with those
 *         abilities. The member takes things from there (new Herd etc.)
 * 
 *         The entry point for a client to be created and used to connect to a running server and
 *         invoke methods on it.
 * 
 * @param The abilities of the member as a String
 */
public class MemberMain {
  private static final Logger LOGGER = Logger.getLogger(MemberMain.class.getName());
  private static Member me;
  private static Herd h;

  public static void main(String[] args) {
    ArrayList<Ability> skills = new ArrayList<Ability>();
    LOGGER.log(Level.INFO, "MemberMain Starting");
    for (String a : args) {
      switch (a) {
        case "PROCESSOR":
          skills.add(Ability.PROCESSOR);
          break;
        case "DRIVER":
          skills.add(Ability.DRIVER);
          break;
        case "SENSOR":
          skills.add(Ability.SENSOR);
          break;
        case "VIEWER":
          skills.add(Ability.VIEWER);
          break;
        case "DEST_SETTER":
          skills.add(Ability.DEST_SETTER);
          break;
        default:
          break;
      }
    }
    if (skills.isEmpty()) {
      LOGGER.log(Level.SEVERE, "No ABLITIES defined as launch arguments");
      System.out.println("Sorry, a Robot needs to have at least one ability!");
    } else {
      Ability[] abilities = new Ability[skills.size()];

      for (int i = 0; i < skills.size(); i++) {
        abilities[i] = skills.get(i);
      }

      me = new Member(abilities);
      LOGGER.log( Level.INFO, "Member Constructed");
      h = new Herd(me); // Here because Herd needs a constructed Member to add.
      LOGGER.log( Level.INFO, "Herd Constructed");

    }
    while (true) {// loop to keep process running for testing
    }

  }

}
