/**
 * 
 */
package interfaces_pre_march_2018;

import common.datatypes.map.Map;

/**
 * @author David Avery
 *
 */
public interface LiDARable {
  //TODO currently a shim for the pass over to arduino... consider: wrapping serial?? refactoring??
  Map sense();//FIXME

}
