package common;

import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Member;
import ui.View;

/**
 * @author Stephen Pope 15836791
 * 
 */
public class Main {

	public static void main (Ability [] args) {
		Member me = new Member(args);
		ArrayList<Ability> skills = me.getAbilities();
		for(Ability a : skills) {
				//TODO add RMI Registry publishing here or in member?
				//TODO should this be a switch?
				if (a.equals(common.datatypes.Ability.VIEWER)) {
					View theObserver = new View(me);
				}
				if (a.equals(common.datatypes.Ability.DEST_SETTER)) {
					//TODO Decorate the View with the DEST_Set options?
				}
				if(a.equals(common.datatypes.Ability.DRIVER)) {
					//TODO Initialise a drive controller
				}
				if (a.equals(common.datatypes.Ability.PROCESSOR)) {
					//TODO Make available for Parallel processing
				}
				if (a.equals(common.datatypes.Ability.SENSOR)) {
					//TODO Initialise a sensor controller to handle incoming data.
				}
			}
		}
	}

