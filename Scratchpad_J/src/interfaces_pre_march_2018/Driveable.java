/**
 * 
 */
package interfaces_pre_march_2018;

import common.datatypes.path.Path;

/**
 * @author David Avery
 *
 */
public interface Driveable {//facade
  //TODO currently a shim for the pass over to arduino... consider: wrapping serial?? refactoring??
  Path drive(Path p);

}
