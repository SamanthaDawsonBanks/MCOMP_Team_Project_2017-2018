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
import common.datatypes.map.griddedMap.Vertex;
import unitTesting.testData.TestData;

/**
 * @author David Avery 15823926
 * @author Harry Jackson 14812630
 *
 */
class MapTesting {

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
    @SuppressWarnings("unused") // only for data-typing
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    l.add((new Waypoint(-1, -1)));
    @SuppressWarnings("unused") // only for data-typing
    Map n = new Map(64, new MapLayer(l));
    @SuppressWarnings("unused") // only for data-typing
    Map m = new Map(64, new MapLayer(TestData.getEmptyCentreMaze())); // TODO expand to full test

    for (Vertex v : m.getAmalgamatedMap().toArrayList()) {
      System.out.println("(" + v.getX() + " " + v.getY() + ")");
    }

    System.out.println(m.getAmalgamatedMap().toArrayList().size());
    
    System.out.printf("");// breakpoint

    // fail("Not yet implemented");
  }

}
