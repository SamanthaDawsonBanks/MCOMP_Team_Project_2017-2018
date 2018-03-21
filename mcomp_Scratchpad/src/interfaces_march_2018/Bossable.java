package interfaces_march_2018;
import common.datatypes.map.*; //TODO these wont work until we move into the MCOMP Java folder
import common.datatypes.path.*;

/**
 * @author Stephen Pope 15836791
 * @author David Avery
 * @author Ryan Shoobert 15812407
 * @author Harry Jackson
 * 
 * Bossable interface
 * 
 * A bossable object is one that can receive instructions from the Leader of a Herd
 * to process a section of a particular item, either a segment of a Map to be augmented
 * or a Path to be analysed.
 * 
 */
public interface Bossable {

	public Map processMapLump(Map);
	public Path processPathLump(Path);	

}
