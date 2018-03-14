/**
 * 
 */
package unitTesting;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.MapLayer;
import common.datatypes.path.Path;
import pathfinding.AStar;

/**
 * @author David Avery
 *
 */
class MapTest2 {

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {}

  /**
   * Test method for {@link common.datatypes.map.Map#addLayer(common.datatypes.map.MapLayer)}.
   */
  @Test
  void testAddLayer() {
    @SuppressWarnings("unused")//only for data-typing
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    l.add((new Waypoint(-1,-1)));
    Map n = new Map(64, new MapLayer(l));
    Map m = new Map(64, new MapLayer(getPresentationMaze())); //TODO expand to full test

    System.out.printf("");// breakpoint

    // fail("Not yet implemented");
    
    Waypoint start = new Waypoint(2,8);
    Waypoint dest = new Waypoint(14,10);
    Waypoint prev = new Waypoint(2,8);
    Path p;
    AStar a = new AStar();
    a.pathfind(start, dest, m);
  }
   
 

  private ArrayList<Waypoint> getPresentationMaze() {
    int y;
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    
    y = 1;
    l.add(new Waypoint(1, y));
    l.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    l.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
    l.add(new Waypoint(7, y));
    l.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    l.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    l.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    l.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    
    y = 2;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    // r.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    // r.add(new Waypoint(6, y));
    // r.add(new Waypoint(7, y));
    // r.add(new Waypoint(8, y));
    // r.add(new Waypoint(9, y));
    // r.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    // r.add(new Waypoint(12, y));
    // r.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    
    y = 3;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    l.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
    l.add(new Waypoint(7, y));
    // r.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    l.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    l.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
   
    y = 4;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    // r.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    // r.add(new Waypoint(6, y));
    // r.add(new Waypoint(7, y));
    // r.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    // r.add(new Waypoint(10, y));
    // r.add(new Waypoint(11, y));
    // r.add(new Waypoint(12, y));
    // r.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    
    y = 5;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
    l.add(new Waypoint(7, y));
    l.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    // r.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    l.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    
    y = 6;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    // r.add(new Waypoint(6, y));
    // r.add(new Waypoint(7, y));
    // r.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    // r.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    // r.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
   
    y = 7;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    l.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    // r.add(new Waypoint(6, y));
    l.add(new Waypoint(7, y));
    // r.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    // r.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    // r.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    
    y = 8;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    // r.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    // r.add(new Waypoint(6, y));
    l.add(new Waypoint(7, y));
    // r.add(new Waypoint(8, y));
    // r.add(new Waypoint(9, y));
    // r.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    // r.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    
    y = 9;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    l.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
    l.add(new Waypoint(7, y));
    l.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    l.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    // r.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    
    y = 10;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    // r.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    // r.add(new Waypoint(6, y));
    // r.add(new Waypoint(7, y));
    // r.add(new Waypoint(8, y));
    // r.add(new Waypoint(9, y));
    // r.add(new Waypoint(10, y));
    // r.add(new Waypoint(11, y));
    // r.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    // r.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));    
    
    y = 11;
    l.add(new Waypoint(1, y));
    l.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    l.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
    l.add(new Waypoint(7, y));
    l.add(new Waypoint(8, y));
    l.add(new Waypoint(9, y));
    l.add(new Waypoint(10, y));
    l.add(new Waypoint(11, y));
    l.add(new Waypoint(12, y));
    l.add(new Waypoint(13, y));
    l.add(new Waypoint(14, y));
    l.add(new Waypoint(15, y));
    return l;

  }

}
