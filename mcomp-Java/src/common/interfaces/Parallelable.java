/**
 * 
 */
package common.interfaces;

import common.datatypes.map.Map;
import common.datatypes.path.Path;

/**
 * @author David Avery
 *
 */
public interface Parallelable {
  Map amalgamate(Map m, Map r);
  Path pathfind(Map m, Path p);
}
