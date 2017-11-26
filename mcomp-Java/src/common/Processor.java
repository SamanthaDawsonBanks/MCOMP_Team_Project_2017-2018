/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public interface Processor {//TODO do we want to extend (and override) vs dual implement for leader vs member??
  LMap processMap(LMap m, LReturn r);//FIXME see leader for LReturn comments
  Path processPath(LMap m, Path p);

}
