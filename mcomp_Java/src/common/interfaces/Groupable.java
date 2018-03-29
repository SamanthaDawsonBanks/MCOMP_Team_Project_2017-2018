package common.interfaces;

import java.util.ArrayList;
import common.datatypes.Ability;
import common.objects.Herd;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         An object is groupable if it can be collected together into a herd
 *
 */
public interface Groupable {
  public boolean joinHerd(Herd h);

  ArrayList<Ability> getAbilities();
  boolean updateLocalHerdInfo(Herd h);
}
