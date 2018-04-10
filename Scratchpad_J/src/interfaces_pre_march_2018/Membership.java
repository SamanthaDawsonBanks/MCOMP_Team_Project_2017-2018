/**
 * 
 */
package interfaces_pre_march_2018;

import java.security.Key;
import java.util.ArrayList;

import common.datatypes.Ability;

/**
 * The interface for Members.
 * 
 * Clases implementing this interface must be able to
 * have abilities, provide a list of those abilities.
 * 
 * They must also be able to use and validate a leaders
 * public key.
 * 
 * @author David Avery
 * @author Stephen Pope 15836791
 *
 */
public interface Membership {
	public ArrayList<Ability> getAbilities();
	public boolean importLeaderKey(Key pk);
	public Key getPublicKey();  
}
