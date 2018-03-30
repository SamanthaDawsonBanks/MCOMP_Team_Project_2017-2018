package common.interfaces;

import common.datatypes.map.Map;
import common.datatypes.path.Path;

/**
 * @author Stephen Pope 15836791
 * @author David Avery 15823926
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson 14812630
 * 
 *         Bossable interface
 * 
 *         A bossable object is one that can receive instructions from the Leader of a Herd to
 *         process a section of a particular item, either a segment of a Map to be augmented or a
 *         Path to be analysed.
 * 
 */
public interface Bossable {

  public Map processMapLump(Map m);

  public Path processPathLump(Path p);

}
