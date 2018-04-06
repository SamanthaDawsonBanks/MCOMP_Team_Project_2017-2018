package pathfind;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.xml.bind.ValidationEventHandler;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.map.griddedMap.BlockedVertex;
import common.datatypes.path.Path;

public class AStarMain {

  Heuristic h = new Heuristic();



  public Path pathfind(Waypoint start, Waypoint dest, Map m) {
    
    Path p = new Path(dest);
    
    ArrayList<Vertex> openList = new ArrayList<Vertex>();
    ArrayList<Vertex> closedList = new ArrayList<Vertex>();

    
    m.getAmalgamatedMap().getVertex(start).gx = 0.0;
    m.getAmalgamatedMap().getVertex(start).hx = h.euclideanHeuristic(start, dest);
    m.getAmalgamatedMap().getVertex(start).fx = m.getAmalgamatedMap().getVertex(start).hx;
    
    openList.add(m.getAmalgamatedMap().getVertex(start));



    while(!openList.isEmpty()) {
      Waypoint current = start;    
      System.out.println("Current node: " + current);
      for(Vertex x: openList) {
        if(x.fx < m.getAmalgamatedMap().getVertex(current).fx){
          m.getAmalgamatedMap().getVertex(current).equals(x);
        }
      }

      if(current.equals(dest)) {
        break;
      }

      openList.remove(m.getAmalgamatedMap().getVertex(current));
      closedList.add(m.getAmalgamatedMap().getVertex(current));
      System.out.println(m.getAmalgamatedMap().getVertex(current).fx);

      for(Vertex v: m.getAmalgamatedMap().getVertex(current).edges) {
        if(!v.equals(m.getAmalgamatedMap().blocked)){
          System.out.println(v.getX() + "," + v.getY() + " ");
        } 
        else if(closedList.contains(v)) {
          System.out.println(v.getX() + "," + v.getY() + "already evaluated");
        }

        Double tempGx = m.getAmalgamatedMap().getVertex(current).gx + h.manhattanHeuristic(current, new Waypoint(v.getX(), v.getY()));

        if(tempGx < v.gx) {
          openList.remove(v);
          closedList.remove(v);
        }
        
        if(!openList.contains(v) && !closedList.contains(v)) {
          v.gx = tempGx;
          v.hx = h.manhattanHeuristic(new Waypoint(v.getX(), v.getY()), dest);
          v.fx = v.gx + v.hx;
          
          v.parente = (m.getAmalgamatedMap().getVertex(current));
          openList.add(v);
          
          
        }
        
        

        
        
           
      }




    }

    return p;
  }
  


}