package pathfind;

import java.util.ArrayList;
import java.util.Collections;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class searchMap {

  ArrayList<Vertex> path = new ArrayList<Vertex>();
  ArrayList<Vertex> openList = new ArrayList<Vertex>();
  ArrayList<Vertex> closedList = new ArrayList<Vertex>();

  Heuristic h = new Heuristic();
  int pathCounter = 0;

  public void search(Waypoint start, Waypoint dest, Map m) {

    m.getAmalgamatedMap().getVertex(start).gx = 0.0;
    m.getAmalgamatedMap().getVertex(start).hx = h.euclideanHeuristic(start, dest);
    m.getAmalgamatedMap().getVertex(start).fx = m.getAmalgamatedMap().getVertex(start).hx;

    openList.add(m.getAmalgamatedMap().getVertex(start));

    while(!openList.isEmpty()) {

      Waypoint current = null;

      if(openList.size()==0) {
        System.out.print("no route");
        throw new RuntimeException("no route");
      }


      if(openList.contains(m.getAmalgamatedMap().getVertex(start))) {
        m.getAmalgamatedMap().getVertex(start).calcF(start, dest);
      }



      for(Vertex v: openList) {
        Waypoint temp = new Waypoint(v.getX(), v.getY()); 
        if(current == null || v.fx <  m.getAmalgamatedMap().getVertex(current).fx) {
          current = temp;
        }
        else if(v.fx == m.getAmalgamatedMap().getVertex(current).fx) {

          if(v.hx < m.getAmalgamatedMap().getVertex(current).hx ) {
            current = temp;
          }

        }
      }


      if(current.getX() == dest.getX() && current.getY() == dest.getY()) {
        returnPath(start, dest, m); 
        System.out.println();
        anotherPath(start, dest, m);
      }



      openList.remove(m.getAmalgamatedMap().getVertex(current));
      closedList.add(m.getAmalgamatedMap().getVertex(current));

      System.out.println("current node: "+ current);

      for(Vertex v: m.getAmalgamatedMap().getVertex(current).edges) {
        if(v == null) {
          continue;
        }


        v.gx = m.getAmalgamatedMap().getVertex(current).gx +1;
        double childGx = v.getG();


        if(!openList.contains(v) && !closedList.contains(v) && !v.equals(m.getAmalgamatedMap().blocked)) {
          v.gx = childGx;
          v.hx = h.manhattanHeuristic(new Waypoint(v.getX(), v.getY()), dest);
          v.fx = v.hx + v.gx;
          v.parente = m.getAmalgamatedMap().getVertex(current);
          openList.add(v);
        }
        if(v.equals(m.getAmalgamatedMap().blocked)) {
          System.out.print("");
        }else if(!closedList.contains(v)) {
          System.out.printf("child:(%d,%d)" + " gx = %.1f" + " hx = %.1f" + " fx = %.1f" +  "\n", v.getX(), v.getY(), v.gx, v.hx, v.fx);
        }

      }

      System.out.print("Open: ");
      for(Vertex v: openList) {
        System.out.print("(" + v.getX() + "," + v.getY() + " fx: " + v.fx + " hx: " + v.hx + " gx: "+ v.gx + ") ");
      }
      System.out.println();
      System.out.print("Closed: ");
      for(Vertex v: closedList) {
        System.out.printf("(%d,%d) ",v.getX(), v.getY());
      }
      System.out.printf("\n \n");
    }

  }

  private void anotherPath(Waypoint start, Waypoint dest, Map m) {
    for(Vertex v: m.getAmalgamatedMap().getVertex(start).edges) {
      if(!closedList.contains(v) && !v.equals(m.getAmalgamatedMap().blocked)) {
        System.out.println();
        System.out.println(v.getX() + "," + v.getY());
      }
    }
  }

  public Path returnPath(Waypoint start, Waypoint dest, Map m) {
    Path p = new Path(dest);
    Waypoint current = dest;
    Vertex v = m.getAmalgamatedMap().getVertex(current);      

    while(v.parente!=m.getAmalgamatedMap().getVertex(start)) {
      p.addNode(new Waypoint(v.getX(), v.getY()));
      v = v.parente;
      path.add(v);
    }
    p.addNode(start);
    path.add(m.getAmalgamatedMap().getVertex(start));
    System.out.printf("\n" + "Total path length: %d" + "\n", p.getLength());
    System.out.print("Path to take:");
    Collections.reverse(path);
    for(Vertex w: path) {
      System.out.printf("(%d,%d) ", w.getX(), w.getY());
    }
    return p;
  }

}
