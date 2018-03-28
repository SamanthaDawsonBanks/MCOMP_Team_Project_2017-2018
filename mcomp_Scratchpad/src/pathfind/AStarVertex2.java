package pathfind;

import java.util.ArrayList;
import java.util.PriorityQueue;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class AStarVertex2 {

  Heuristic h = new Heuristic();


  public Path pathfind(Waypoint start, Waypoint dest, Map m) {

    ArrayList<Vertex> openList = new ArrayList<Vertex>();
    ArrayList<Vertex> closedList = new ArrayList<Vertex>();
    ArrayList<Vertex> blockedList = new ArrayList<Vertex>();
    ArrayList<Double> fScores = new ArrayList<Double>();
    
    
    openList.add(m.getAmalgamatedMap().getVertex(start));
    Path p = new Path(start);
    p.addNode(start);
    
    m.getAmalgamatedMap().getVertex(start).gx = 0.0;
    m.getAmalgamatedMap().getVertex(start).hx = h.euclideanHeuristic(start, dest);
    m.getAmalgamatedMap().getVertex(start).fx = m.getAmalgamatedMap().getVertex(start).hx;
    
    
   
    
    while(!openList.isEmpty()) {
      Waypoint current = null;
      
      for(Vertex v: openList) {
        Waypoint tempCurrent = new Waypoint(v.getX(), v.getY()); 
        if(current == null || v.fx < m.getAmalgamatedMap().getVertex(current).fx) {
          current = tempCurrent;
          openList.remove(m.getAmalgamatedMap().getVertex(current));
         
        }
      }
      if(current.equals(dest)) {
        break;
      }
      
    }


    return p;

  }
  
  
  public int compare(Waypoint a, Waypoint b) {
//    if(a.getF() < b.getF()) {
//      return -1;
//    }
//    else if(a.getF() > b.getF()) {
//      return 1;
//    }
//    else {
      return 0;
//    }
  }
  

}

