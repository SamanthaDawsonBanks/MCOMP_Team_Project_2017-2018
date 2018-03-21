package pathfinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class AStar {

  ArrayList<Waypoint> openList = new ArrayList<Waypoint>();
  ArrayList<Double> open = new ArrayList<Double>();
  ArrayList<Waypoint> closedList = new ArrayList<Waypoint>();
  ArrayList<Waypoint> blockedList = new ArrayList<Waypoint>();
  ArrayList<Double> fScores = new ArrayList<Double>();

  ArrayList<Waypoint>checked = new ArrayList<Waypoint>();
  Waypoint current, prev;
  private double f = 0.0;
  private double g;
  Heuristic h = new Heuristic();
  Path p = new Path(current);
  int counter = 0;
  HashSet<Waypoint> tmp = new HashSet<Waypoint>();


  public void pathfind(Waypoint start, Waypoint dest, Map m) {

    if(openList.isEmpty()==true) {
      current = start;
      closedList.add(start);
      openList.remove(start);
    }
 
    
    if(m.getAmalgamatedMap().getVertex(current).edges.equals(prev)) {
      System.out.print("lol");
    }

    while(counter<3) {
      System.out.println("Current Node: " + current);
  
      
      for(Vertex v: m.getAmalgamatedMap().getVertex(current).edges) {
        Waypoint tempCurrent = new Waypoint(v.getX(), v.getY());        
        if(tempCurrent.x == 0 && tempCurrent.y == 0) {
          System.out.println("(" + v.getX() + "," + v.getY() + ") " + ": Blocked");
          blockedList.add(tempCurrent);
        }
        else { 
          f = h.euclideanHeuristic(tempCurrent, dest) + 1.0;
          fScores.add(f);
          System.out.println("(" + tempCurrent.getX() + ", " + tempCurrent.getY() + ") " + ": " + f);
          openList.add(tempCurrent); 
          
        }

        /**
       for(Waypoint w: openList) {
          for(Waypoint w2: closedList) {
             if(w2.getX() == w.getX() && w2.getY() == w.getY()) {
               if(openList.contains(current)) {
               checked.add(w);
               fScores.remove(w);
               }
             }
          }
        }
        openList.removeAll(checked);
         */

      }

      System.out.println("openList size: " + openList.size());
      System.out.println("closedList size: " + closedList.size());
      System.out.println("openList: " + openList);
      System.out.println("closedList: " + closedList);
      System.out.println("blockedList size: " + blockedList.size());
      System.out.println("Fscores: " + fScores);
      System.out.println("");

      lowestFscore(fScores);
      int x = lowestFscore(fScores);
      current = openList.get(x);
      closedList.add(current);
      openList.remove(current);
      fScores.remove(x);

      counter++;
    }  


  }


  public static <F extends Comparable<F>> int lowestFscore(final List<F> fScores) {
    int minIndex;
    if (fScores.isEmpty()) {
      minIndex = -1;
    } else {
      final ListIterator<F> itr = fScores.listIterator();
      F min = itr.next(); // first element as the current minimum
      minIndex = itr.previousIndex();
      while (itr.hasNext()) {
        final F curr = itr.next();
        if (curr.compareTo(min) < 0) {
          min = curr;
          minIndex = itr.previousIndex();
        }
      }
    }
    return minIndex;
  }

  public ArrayList<Waypoint> getChildren(Waypoint current, Map m) {
    //Get all nodes from start vertex
    for(Vertex v: m.getAmalgamatedMap().getVertex(current).edges) {
      Waypoint tempCurrent = new Waypoint(v.getX(), v.getY());

      if(tempCurrent.x == 0 && tempCurrent.y == 0) {
        System.out.println("(" + v.getX() + "," + v.getY() + ") " + ": Blocked");
        blockedList.add(tempCurrent);
      }
      else {
        System.out.println("(" + v.getX() + "," + v.getY() + ") ");
        openList.add(tempCurrent);
      }
    }
    System.out.println("openList size: " + openList.size());
    System.out.println("closedList size: " + closedList.size());
    System.out.println("blockedList size: " + blockedList.size());
    System.out.println("");
    return closedList;
  }


  public double scoreChildren(Waypoint current, Waypoint dest) {
    for(int x = 0; x < openList.size(); x++) {
      f = h.euclideanHeuristic(current, dest) + g(current, dest);
      System.out.println(f);
    }
    return f;
  }

  public double g(Waypoint current, Waypoint dest) {
    return g = 1;
  }


  public void optimisePath(Path p) {

  }


}
