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
public interface Thinkable {//TODO do we want to extend (and override) vs dual implement for leader vs member??
  Map processMap(Map m, Map r);//FIXME see leader for LReturn comments
  Path processPath(Map m, Path p);

}
