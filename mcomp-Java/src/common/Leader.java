/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public interface Leader {
  Map amalgamate(Map m, Map r);//is a LReturn just a map? if so then amalgamate can be reused if not then could be iterated
  //but then what is a map?? is it flat (pre amalgamated) or a stack??
  //does it matter?? as if it is a stack then a 'simple' return is just a stack of 1 element??
  //FIXME
  Path pathfind(Map m, Path p);
}
