package common;

import java.util.ArrayList;
import java.util.Queue;

import common.datatypes.Ability;

public class Main {

	public static void main (Ability [] args) {
		ArrayList<Ability> skills = new ArrayList();
		for(Ability a : args) {
			skills.add(a);			
		}
	}
}
