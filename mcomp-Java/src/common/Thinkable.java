/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public interface Thinkable {//TODO do we want to extend (and override) vs dual implement for leader vs member??
  LMap processMap(LMap m, LMap r);//FIXME see leader for LReturn comments
  Path processPath(LMap m, Path p);

}
