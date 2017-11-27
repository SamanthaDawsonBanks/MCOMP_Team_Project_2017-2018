/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public interface Driveable {//facade
  //TODO currently a shim for the pass over to arduino... consider: wrapping serial?? refactoring??
  Path drive(Path p);

}
