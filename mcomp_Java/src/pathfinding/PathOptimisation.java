package pathfinding;

import java.util.ArrayList;

import common.datatypes.Waypoint;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;


/**
 * This class handles the Path object returned from the pathfinding algorithm.
 * The methods in this class act on the current path and retrieve data associated 
 * with the Path object, or a new instance of it (optimised version).
 * 
 * @author Harry Jackson 14812630
 * 
 * @version 1.0
 * @since 2018-04-20
 * 
 * @see pathfinding.ScoredVertex
 * @see pathfinding.PathOptimisation
 * @see pathfinding.Heuristic
 * @see common.datatypes.map.
 * @see common.datatypes.path.Path
 * 
 */
public class PathOptimisation {
  boolean devMode = false;

  /**
   * Method for returning the total number of turns from the returned Path.
   * 
   * @param ArrayList<ScoredVertex> the arrayList to be iterated through.
   * The arrayList is passed in a copy of the returned Path object.
   * 
   * @return turnCounter the total number of turns in the Path. 
   */
  public int getTurns(ArrayList<ScoredVertex> routeToStart) {
    int counter = 2;
    int turnCounter = 0;
    for(Vertex v: routeToStart) {
      if(counter < routeToStart.size()) {
        Vertex future = routeToStart.get(counter);
        if(future.getX() != v.getX() && future.getY() != v.getY()) {
          turnCounter++;
        }
        counter++;
      }
    }
    if(devMode) {
      System.out.printf("Turns in route: %d" + "\n" ,turnCounter);
    }
    return turnCounter;
  }

  /**
   * Method is used to reduce the total length of the Path item.
   * With the use of RMI, sending large amount data is inefficient and slow.
   * 
   * @param ArrayList<ScoredVertex> the arrayList to be iterated through.
   * The arrayList is passed in a copy of the returned Path object.
   * 
   * @return the reduced path object.
   */
  public Path getShorterPath(ArrayList<ScoredVertex> path){
    ArrayList<Vertex> newPath = new ArrayList<Vertex>();
    Path p = new Path(null);
    for(int i = 0; i<path.size(); i++) {
      Vertex current = path.get(i);
      if(i == 0 || i==path.size()-1) {
        newPath.add(current);
        p.addNode(new Waypoint(current.getX(), current.getY()));
      }
      if(i!=path.size()-1) {
        Vertex next  = path.get(i+1);
        if(i!=0) {
          Vertex prev = path.get(i-1);
          if(prev.getX() != next.getX() && prev.getY() != next.getY()) {
            newPath.add(current);
            p.addNode(new Waypoint(current.getX(), current.getY()));
          }
        }
      }
    }
    if(devMode) {
      System.out.print("New path to take: ");
      for(Vertex v: newPath) {
        System.out.printf("(%d,%d) ",v.getX(), v.getY());
      }
      System.out.printf("\n" + "New total path length: " + "%d", p.getLength());
    }
    return p;
  }
  
  
//  /**
//   * Method for preventing information getting printed to the console.
//   * At runtime printing out all variables is useful for debugging.
//   * But not in a realtime situation/environment.
//   * 
//   * @return devMode a boolean check
//   */
//  public Boolean devMode() {
//    boolean devMode = false;
//    return devMode;
//  }

}