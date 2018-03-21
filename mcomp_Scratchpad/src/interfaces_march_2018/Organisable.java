package interfaces_march_2018;

import common.objects.Member;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Organisable interface
 * 
 * An object is organisable if it supports the ability to hold an election and 
 * select a Leader.
 * 
 */

public interface Organisable {

	public Member electLeader();
	
}
