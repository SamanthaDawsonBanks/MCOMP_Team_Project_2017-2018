package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.BlockedVertex;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

/**
 * The AStar must take a 'destination' Waypoint, 'Starting' Waypoint and a Map as part of its
 * instantiation in order to pathfind. It will always return a Path object given that the
 * destination can be reached from the starting Waypoint.
 * 
 * @author Harry Jackson 14812630
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-20
 * 
 * @see common.datatypes.map.griddedMap.GriddedMap
 * @see common.datatypes.map.griddedMap.Vertex
 * @see common.datatypes.map.griddedMap.Map
 * @see common.datatypes.path.Path
 * @see common.datatypes.Waypoint
 * 
 */
public class AStar {
  boolean devMode = false;

  // Intialise ArrayLists for handling ScoredVertex's.
  private ArrayList<ScoredVertex> routeToStart = new ArrayList<ScoredVertex>();
  private ArrayList<ScoredVertex> openList = new ArrayList<ScoredVertex>();
  private ArrayList<ScoredVertex> closedList = new ArrayList<ScoredVertex>();

  // Initialise PathOptimisation object for dealing with the returned path.
  private PathOptimisation pOpt = new PathOptimisation();

  // Initialise Heuristic Object for handling the hx cost.
  private Heuristic h = new Heuristic();

  /**
   * This is the main method for pathfinding through a Map. It scores Vertices as it progressively
   * works towards the destination by implementing the AStar search Algorithm.
   * 
   * @see common.datatypes.map.griddedMap.Map
   * @see common.datatypes.path.Path
   * @see common.datatypes.Waypoint
   * @see common.datatypes.map.griddedMap.Vertex
   * @see pathfinding.Heuristic
   * @see pathfinding.PathOptimisation
   * @see pathfinding.ScoredVertex
   * 
   * @param Waypoint start the robots starting position
   * @param Waypoint dest the target destination
   * @param Map m the space to be searced by the Pathfinding algorithm.
   * 
   * @return Path the path object.
   * 
   */
  public Path pathfind(Waypoint start, Waypoint dest, Map m) {

    ScoredVertex svStart =
        new ScoredVertex(m.getAmalgamatedMap().getVertex(start), m.getAmalgamatedMap());
    ScoredVertex svDest =
        new ScoredVertex(m.getAmalgamatedMap().getVertex(dest), m.getAmalgamatedMap());
    BlockedVertex blocked = m.getAmalgamatedMap().blocked;

    int counter = 0; // for debugging

    svStart.setGx(0.0);
    svStart.setHx(h.manhattanHeuristic(svStart, svDest));

    openList.add(svStart);
    ScoredVertex current = svStart;

    /**
     * runs while the OpenList is not empty. This is because the if the OpenList is empty and the
     * destination has not been reached then there are no more Vertices to be explored meaning the
     * Destination is unreachable, and a runtime error occurs.
     */
    while (!openList.isEmpty() && counter < 15) {

      current = openList.get(0);

      /**
       * Iterates through openList and sets the current ScoredVertex equal to the child with the
       * Lowest fx cost. If the fx cost of two or more children are equal we then select the child
       * with the lower hx cost.
       */
      for (ScoredVertex v : openList) {
        if (v.getFx() < current.getFx()) {
          current.setDirection(current, v);
          current = v;
        }
        if (v.getFx() == current.getFx()) {
          if (v.getHx() < current.getHx()) {
            current.setDirection(current, v);
            current = v;
          }
        }
      }

      /**
       * Checks if the current ScoredVertex equals the destination ScoredVertex. If true the goal
       * has been reached. We call the getPath() method and then Optimise the path object and return
       * from the pathfind method.
       */
      if (isEquals(current, svDest)) {
        if (devMode) {
          System.out.println("goal reached ");
          System.out.println("Closed List: " + closedList.size());
          System.out.print("Open List: " + openList.size());
        }
        getPath(svStart, current, m);
        pOpt.getTurns(routeToStart);
        return pOpt.getShorterPath(routeToStart);
      }

      // counter++; uncomment to debug

      openList.remove(current);
      closedList.add(current);
      if (devMode) {
        System.out.println("current node: " + "(" + current.getX() + "," + current.getY() + ") ");
        System.out.print("Facing: " + current.getDirection() + " ");
        System.out.println();
      }

      /**
       * We iterate through the edges of the current ScoredVertex to get the neighbours adjacent to
       * current. We set each neighbours direction, the parent equal to current (used for returning
       * the path in getPath() method. hx cost (heuristic cost to destination), gx cost (real cost
       * from the start ScoredVertex to the child), fx cost (fx = hx + gx).
       */
      for (Vertex v : current.edges) {

        if (v == null || v.equals(blocked) || myContains(closedList, v)) {
          // FIXME null should be open?
          continue;
        }

        ScoredVertex z = new ScoredVertex(v, m.getAmalgamatedMap());
        z.setDirection(current, z);
        // z.setHx(h.manhattanHeuristic(z, svDest));
        z.setHx(h.straightLineHeuristic(z, svDest, current, z));

        if (!myContains(openList, z)) {
          openList.add(z);
        }

        double childGx = current.getGx() + 1;

        if (childGx < z.getGx()) {
          continue;
        }

        z.setGx(childGx);
        z.parent = current;

        if (!closedList.contains(z)) {
          if (devMode) {
            System.out.printf(
                "child:(%d,%d)" + " gx = %.1f" + " hx = %.1f" + " fx = %.1f" + " dir = %s" + "\n",
                z.getX(), z.getY(), z.getGx(), z.getHx(), z.getFx(), z.getDirection());
          }
        }
      }

      if (devMode) {
        System.out.println();
        System.out.print("Open: ");
        for (ScoredVertex v : openList) {
          System.out.print("(" + v.getX() + "," + v.getY() + " fx: " + v.getFx() + " hx: "
              + v.getHx() + " gx: " + v.getGx() + ") ");
        }
        System.out.println();
        System.out.print("Closed: ");
        for (ScoredVertex v : closedList) {
          System.out.print("(" + v.getX() + "," + v.getY() + ") ");
        }
        System.out.printf("\n \n");
      }
    }

    System.out.print("no route");
    throw new RuntimeException("no route");

  }

  /**
   * This method is called when the destination has been reached. It is used to return to the intial
   * Path object found from pathfinding algorithm.
   * 
   * @see common.datatypes.map.griddedMap.Map
   * @see common.datatypes.path.Path
   * @see common.datatypes.Waypoint
   * @see common.datatypes.map.griddedMap.Vertex
   * @see pathfinding.ScoredVertex
   * 
   * @param ScoredVertex start the specialised version of vertex representing the starting point.
   * @param ScoredVertex dest the specialised version of vertex representing the target destination.
   * @param Map m an empty copy of the Map object used to return the found Path.
   * 
   * @return Path the path object.
   * 
   */
  public Path getPath(ScoredVertex start, ScoredVertex dest, Map m) {
    ScoredVertex current = dest;
    routeToStart.add(dest);

    while (!isEquals(current, start)) {
      current = (ScoredVertex) current.parent;
      routeToStart.add((ScoredVertex) current);
    }
    if (devMode) {
      System.out.print("\n" + "Path to take:");
    }
    Collections.reverse(routeToStart);
    Path p = new Path(new Waypoint(dest.getX(), dest.getY()));

    for (Vertex w : routeToStart) {
      p.addNode(new Waypoint(w.getX(), w.getY()));
      if (devMode) {
        System.out.printf("(%d,%d) ", w.getX(), w.getY());
      }
    }
    if (devMode) {
      System.out.printf("\n" + "Total routeToStart length: %d" + "\n", p.getLength());
    }
    return p;
  }

  /**
   * This method is used to compare two vertices to check if they are equal.
   * 
   * @see common.datatypes.map.griddedMap.Vertex
   * 
   * @param Vertex a
   * @param Vertex b
   * 
   * @return Boolean checking if Vertex a equals Vertex b
   * 
   */
  public boolean isEquals(Vertex a, Vertex b) {
    return (a.getX() == b.getX() && a.getY() == b.getY());
  }

  /**
   * This method is a specialised version of the Java String class contains() method. We iterate
   * through the arrayList of ScoredVertex's and call the isEquals() method to check if the
   * ArrayList contains a specific Vertex. This is required because we are dealing with
   * ScoredVertex's and Vertex's which are not equivalent.
   * 
   * @see common.datatypes.map.griddedMap.Vertex
   * @see pathfinding.ScoredVertex
   * 
   * @param ArrayList<ScoredVertex> the Collection to be iterated through.
   * @param Vertex v, the Vertex being checked if it exist within the Collection.
   * 
   * @return Boolean if arrayList<ScoredVertex> l contains Vertex v
   * 
   */
  public boolean myContains(ArrayList<ScoredVertex> l, Vertex v) {
    boolean res = false;
    for (Vertex sv : l) {
      if (isEquals(sv, v)) {
        res = true;
      }
    }
    return res;
  }

}
