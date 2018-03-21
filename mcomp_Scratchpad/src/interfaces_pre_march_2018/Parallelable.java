/**
 * 
 */
package interfaces_pre_march_2018;

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
