/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public interface Sensor {
  //TODO currently a shim for the pass over to arduino... consider: wrapping serial?? refactoring??
  LReturn sense();//FIXME

}
