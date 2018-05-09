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
import pathfinding.AStar;
import unitTesting.testData.TestData;

/**
 * @author Harry Jackson 14812630
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
    @SuppressWarnings("unused")
    Map n = new Map(64, new MapLayer(l));
    Map m = new Map(64, new MapLayer(TestData.getEmptyMaze())); //TODO expand to full test

    System.out.printf("");// breakpoint

    // fail("Not yet implemented");

    Waypoint start = new Waypoint(2,2);
    Waypoint dest = new Waypoint(5,5);

    AStar a = new AStar();
    a.pathfind(start, dest, m);
    
    //SearchMap b = new SearchMap();
   // b.search(start, dest, m);
  }

}
