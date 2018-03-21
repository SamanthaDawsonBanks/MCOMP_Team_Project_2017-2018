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
class MapTestEmpty
{

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
    
    Waypoint start = new Waypoint(2,2);
    Waypoint dest = new Waypoint(5.0, 4.0);

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

    
    y = 2;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    // r.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
   
    
    y = 3;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
   
    y = 4;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    // r.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));

    y = 5;
    l.add(new Waypoint(1, y));
    // r.add(new Waypoint(2, y));
    // r.add(new Waypoint(3, y));
    // r.add(new Waypoint(4, y));
    // r.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
    
    y = 5;
    l.add(new Waypoint(1, y));
    l.add(new Waypoint(2, y));
    l.add(new Waypoint(3, y));
    l.add(new Waypoint(4, y));
    l.add(new Waypoint(5, y));
    l.add(new Waypoint(6, y));
    
  
    return l;

  }

}
