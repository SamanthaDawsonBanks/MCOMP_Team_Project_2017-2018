/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public interface Thinkable {//TODO do we want to extend (and override) vs dual implement for leader vs member??
  Map processMap(Map m, Map r);//FIXME see leader for LReturn comments
  Path processPath(Map m, Path p);

}
