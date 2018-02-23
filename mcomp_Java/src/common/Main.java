package common;

import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Member;
import ui.View;

public class Main {

	public static void main (Ability [] args) {
		Member me = new Member(args);
		ArrayList<Ability> skills = me.getAbilities();
		for(Ability a : skills) {
				if (a.equals(common.datatypes.Ability.VIEWER)) {
					View theObserver = new View(me);
				}
			}
		}
	}

