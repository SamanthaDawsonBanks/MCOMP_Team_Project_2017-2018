package pathfind;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;

/**
 * @author Harry Jackson
 * @version 1.1
 * 
 * A* pathfinding class
 * Initialise openList of type Vertex - Vertices not yet evaluated.
 * Initialise closedList of type Vertex - Vertices that have been evaluated.
 * Initialise blockedList of type Vertex - Vertices that are blocked.
 * Initialise fScores ArrayList of type double - holds information regarding each vertex's f(x) score.
 * 
 */

public class AStarVertex {

  private ArrayList<Vertex> openList = new ArrayList<Vertex>();
  private ArrayList<Vertex> closedList = new ArrayList<Vertex>();
  private ArrayList<Vertex> blockedList = new ArrayList<Vertex>();
  private ArrayList<Double> fScores = new ArrayList<Double>();
  private double f = 0.0;
  private double g;
  private Heuristic h = new Heuristic();
  public Waypoint current;
  public int counter = 0;
  public Vertex v;
  public Boolean isReached = false;


  /**
   * The Method for pathfinding.
   * Takes in three formal parameters:
   * - The start and the destination Waypoints
   * - A Map
   * 
   * TODO return A path.
   */

  public void pathfind(Waypoint start, Waypoint dest, Map m) {
    
    
    if(openList.isEmpty()==true) {
      current = start;
      openList.remove(m.getAmalgamatedMap().getVertex(current));
      closedList.add(m.getAmalgamatedMap().getVertex(current));
    }


    while(isReached==false){
      System.out.println("Current Node: " + current);

      for(Vertex v: m.getAmalgamatedMap().getVertex(current).edges){ 

        Waypoint tempCurrent = new Waypoint(v.getX(), v.getY()); 
        if(tempCurrent.x == 0 && tempCurrent.y == 0) {
          System.out.println("(" + v.getX() + "," + v.getY() + ") " + ": Blocked");
          blockedList.add(v);
        }
        else if(closedList.contains(v)) {
          System.out.println("(" + v.getX() + "," + v.getY() + ") " + ": Already checked");
        }

        else {  
          f = h.manhattanHeuristic(tempCurrent, dest) + g;
          fScores.add(f);
          Vertex parent = m.getAmalgamatedMap().getVertex(current);
          System.out.println("(" + v.getX() + "," + v.getY() + ") " + ": " + f);
          openList.add(v);        
        }

      }
      
      System.out.print("Open: ");
      for(Vertex v: openList) {
        System.out.print( "(" + v.getX() + "," + v.getY() + ")" );
      }
      System.out.println();
      System.out.print("Closed: ");
      for(Vertex v: closedList) {
        System.out.print( "(" + v.getX() + "," + v.getY() + ")" );
      }
      System.out.println();
      System.out.println("Fscores: " + fScores);

      lowestFscore(fScores);
      int x = lowestFscore(fScores);   
      v = openList.get(x);
      closedList.add(v);    
      current = new Waypoint(v.getX(), v.getY());
      openList.remove(m.getAmalgamatedMap().getVertex(current));
      fScores.remove(x);
      System.out.println();
      counter++;

      if(v.getX()==dest.getX()&& v.getY()==dest.getY()) {
        isReached = true;
      }

    }
  }


  /**
   * The Method for retreiving index of lowest f(x) score from ArrayList.
   * 
   * @return Index at which the minimum f(x) score is located in ArrayList. 
   */
  public static <F extends Comparable<F>> int lowestFscore(final List<F> fScores) {
    int minFxIndex;
    if (fScores.isEmpty()) {
      minFxIndex = -1;
    } else {
      final ListIterator<F> itr = fScores.listIterator();
      F min = itr.next(); 
      minFxIndex = itr.previousIndex();
      while (itr.hasNext()) {
        final F curr = itr.next();
        if (curr.compareTo(min) < 0) {
          min = curr;
          minFxIndex = itr.previousIndex();
        }
      }
    }
    return minFxIndex;
  }

}






