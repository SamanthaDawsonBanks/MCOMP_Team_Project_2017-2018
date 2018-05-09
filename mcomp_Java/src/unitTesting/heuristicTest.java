package unitTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import common.datatypes.Waypoint;
import common.datatypes.map.griddedMap.Vertex;
import pathfinding.Heuristic;

class heuristicTest {

  @Test
  void test() {
    Vertex current = new Vertex(new Waypoint(0,0), null);
    Vertex dest = new Vertex(new Waypoint(3,3), null);
    
    
    Heuristic h = new Heuristic();
    
    h.manhattanHeuristic(current, dest);
    
    System.out.print(h.manhattanHeuristic(current, dest));
  }

}
