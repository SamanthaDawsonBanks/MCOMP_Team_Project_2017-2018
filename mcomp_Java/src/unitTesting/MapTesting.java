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

/**
 * @author David Avery
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
    @SuppressWarnings("unused")//only for data-typing
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    l.add((new Waypoint(-1,-1)));
    Map n = new Map(64, new MapLayer(l));
    Map m = new Map(64, new MapLayer(getPresentationMaze())); //TODO expand to full test

    System.out.printf("");// breakpoint

    // fail("Not yet implemented");
  }


  private ArrayList<Waypoint> getPresentationMaze() {
    int y;
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    y = 1;
    l.add(new Waypoint(y, 1));
    l.add(new Waypoint(y, 2));
    l.add(new Waypoint(y, 3));
    l.add(new Waypoint(y, 4));
    l.add(new Waypoint(y, 5));
    l.add(new Waypoint(y, 6));
    l.add(new Waypoint(y, 7));
    l.add(new Waypoint(y, 8));
    l.add(new Waypoint(y, 9));
    l.add(new Waypoint(y, 10));
    l.add(new Waypoint(y, 11));
    l.add(new Waypoint(y, 12));
    l.add(new Waypoint(y, 13));
    l.add(new Waypoint(y, 14));
    l.add(new Waypoint(y, 15));
    y = 2;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    // r.add(new Waypoint(y,3));
    // r.add(new Waypoint(y,4));
    // r.add(new Waypoint(y,5));
    // r.add(new Waypoint(y,6));
    // r.add(new Waypoint(y,7));
    // r.add(new Waypoint(y,8));
    // r.add(new Waypoint(y,9));
    // r.add(new Waypoint(y,10));
    l.add(new Waypoint(y, 11));
    // r.add(new Waypoint(y,12));
    // r.add(new Waypoint(y,13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 3;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    l.add(new Waypoint(y, 3));
    l.add(new Waypoint(y, 4));
    l.add(new Waypoint(y, 5));
    l.add(new Waypoint(y, 6));
    l.add(new Waypoint(y, 7));
    // r.add(new Waypoint(y,8));
    l.add(new Waypoint(y, 9));
    l.add(new Waypoint(y, 10));
    l.add(new Waypoint(y, 11));
    l.add(new Waypoint(y, 12));
    l.add(new Waypoint(y, 13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 4;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    // r.add(new Waypoint(y,3));
    // r.add(new Waypoint(y,4));
    // r.add(new Waypoint(y,5));
    // r.add(new Waypoint(y,6));
    // r.add(new Waypoint(y,7));
    // r.add(new Waypoint(y,8));
    l.add(new Waypoint(y, 9));
    // r.add(new Waypoint(y,10));
    // r.add(new Waypoint(y,11));
    // r.add(new Waypoint(y,12));
    // r.add(new Waypoint(y,13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 5;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    l.add(new Waypoint(y, 3));
    // r.add(new Waypoint(y,4));
    l.add(new Waypoint(y, 5));
    l.add(new Waypoint(y, 6));
    l.add(new Waypoint(y, 7));
    l.add(new Waypoint(y, 8));
    l.add(new Waypoint(y, 9));
    // r.add(new Waypoint(y,10));
    l.add(new Waypoint(y, 11));
    l.add(new Waypoint(y, 12));
    l.add(new Waypoint(y, 13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 6;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    l.add(new Waypoint(y, 3));
    // r.add(new Waypoint(y,4));
    l.add(new Waypoint(y, 5));
    // r.add(new Waypoint(y,6));
    // r.add(new Waypoint(y,7));
    // r.add(new Waypoint(y,8));
    l.add(new Waypoint(y, 9));
    // r.add(new Waypoint(y,10));
    l.add(new Waypoint(y, 11));
    // r.add(new Waypoint(y,12));
    l.add(new Waypoint(y, 13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 7;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    l.add(new Waypoint(y, 3));
    l.add(new Waypoint(y, 4));
    l.add(new Waypoint(y, 5));
    // r.add(new Waypoint(y,6));
    l.add(new Waypoint(y, 7));
    // r.add(new Waypoint(y,8));
    l.add(new Waypoint(y, 9));
    // r.add(new Waypoint(y,10));
    l.add(new Waypoint(y, 11));
    // r.add(new Waypoint(y,12));
    l.add(new Waypoint(y, 13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 8;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    // r.add(new Waypoint(y,3));
    // r.add(new Waypoint(y,4));
    // r.add(new Waypoint(y,5));
    // r.add(new Waypoint(y,6));
    l.add(new Waypoint(y, 7));
    // r.add(new Waypoint(y,8));
    // r.add(new Waypoint(y,9));
    // r.add(new Waypoint(y,10));
    l.add(new Waypoint(y, 11));
    // r.add(new Waypoint(y,12));
    l.add(new Waypoint(y, 13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 9;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    l.add(new Waypoint(y, 3));
    l.add(new Waypoint(y, 4));
    l.add(new Waypoint(y, 5));
    l.add(new Waypoint(y, 6));
    l.add(new Waypoint(y, 7));
    l.add(new Waypoint(y, 8));
    l.add(new Waypoint(y, 9));
    l.add(new Waypoint(y, 10));
    l.add(new Waypoint(y, 11));
    // r.add(new Waypoint(y,12));
    l.add(new Waypoint(y, 13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 10;
    l.add(new Waypoint(y, 1));
    // r.add(new Waypoint(y,2));
    // r.add(new Waypoint(y,3));
    // r.add(new Waypoint(y,4));
    // r.add(new Waypoint(y,5));
    // r.add(new Waypoint(y,6));
    // r.add(new Waypoint(y,7));
    // r.add(new Waypoint(y,8));
    // r.add(new Waypoint(y,9));
    // r.add(new Waypoint(y,10));
    // r.add(new Waypoint(y,11));
    // r.add(new Waypoint(y,12));
    l.add(new Waypoint(y, 13));
    // r.add(new Waypoint(y,14));
    l.add(new Waypoint(y, 15));
    y = 11;
    l.add(new Waypoint(y, 1));
    l.add(new Waypoint(y, 2));
    l.add(new Waypoint(y, 3));
    l.add(new Waypoint(y, 4));
    l.add(new Waypoint(y, 5));
    l.add(new Waypoint(y, 6));
    l.add(new Waypoint(y, 7));
    l.add(new Waypoint(y, 8));
    l.add(new Waypoint(y, 9));
    l.add(new Waypoint(y, 10));
    l.add(new Waypoint(y, 11));
    l.add(new Waypoint(y, 12));
    l.add(new Waypoint(y, 13));
    l.add(new Waypoint(y, 14));
    l.add(new Waypoint(y, 15));

    return l;

  }

}
