package unitTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import common.datatypes.Waypoint;
import pathfinding.Heuristic;

class heuristicTest {

  @Test
  void test() {
    Waypoint current = new Waypoint(0,0);
    Waypoint dest = new Waypoint(3,3);
    
    
    Heuristic h = new Heuristic();
    
    h.manhattanHeuristic(current, dest);
    
    System.out.print(h.manhattanHeuristic(current, dest));
  }

}
