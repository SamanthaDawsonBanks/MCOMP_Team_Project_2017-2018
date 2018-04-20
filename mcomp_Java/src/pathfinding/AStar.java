package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.BlockedVertex;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class AStar {

  private Heuristic h = new Heuristic();

  private ArrayList<ScoredVertex> routeToStart = new ArrayList<ScoredVertex>();
  private ArrayList<ScoredVertex> openList = new ArrayList<ScoredVertex>();
  private ArrayList<ScoredVertex> closedList = new ArrayList<ScoredVertex>();

  public Path pathfind(Waypoint start, Waypoint dest, Map m) {

    PathOptimisation pOpt = new PathOptimisation();

    ScoredVertex svStart =
        new ScoredVertex(m.getAmalgamatedMap().getVertex(start), m.getAmalgamatedMap());
    ScoredVertex svDest =
        new ScoredVertex(m.getAmalgamatedMap().getVertex(dest), m.getAmalgamatedMap());

    BlockedVertex blocked = m.getAmalgamatedMap().blocked;

    int counter = 0; //for debugging

    svStart.setGx(0.0);
    svStart.setHx(h.manhattanHeuristic(svStart, svDest));

    openList.add(svStart);

    ScoredVertex current = svStart;

    while (!openList.isEmpty() && counter < 15) {

      current = openList.get(0);

      for (ScoredVertex v : openList) {
        if (v.getFx() < current.getFx()) {
          current = v;
        }
         if (v.getFx() == current.getFx()) {
         if (v.getHx() < current.getHx()) {
         current = v;
         }
         }
      }

      if (isEquals(current, svDest)) {
        System.out.print("goal reached ");
        System.out.print(closedList.size());
        return returnPath(svStart, current, m);
      }

      //counter++; uncomment to debug


      openList.remove(current);
      closedList.add(current);
      System.out.println("current node: " + current.getX() + "," + current.getY() + " (" + "fx: "
          + current.getFx() + " hx: " + current.getHx() + " gx: " + current.getGx() + ") ");
      System.out.println();


      for (Vertex v : current.edges) {

        if (v == null || v.equals(blocked) || myContains(closedList, v)) {
          // FIXME null should be open?
          continue;
        }

        ScoredVertex z = new ScoredVertex(v, m.getAmalgamatedMap());
        z.setHx(h.manhattanHeuristic(z, svDest));

        if (!myContains(openList, z)) {
          openList.add(z);
        }

        double childGx = z.getGx() + 1;

        if (childGx < z.getGx()) {
          continue;
        }

        z.setGx(childGx);
        z.parent = current;

        if (!closedList.contains(z)) {
          System.out.printf("child:(%d,%d)" + " gx = %.1f" + " hx = %.1f" + " fx = %.1f" + "\n",
              z.getX(), z.getY(), z.getGx(), z.getHx(), z.getFx());
        }
      }

      System.out.println();
      System.out.print("Open: ");
      for (ScoredVertex v : openList) {
        System.out.println("(" + v.getX() + "," + v.getY() + " fx: " + v.getFx() + " hx: "
            + v.getHx() + " gx: " + v.getGx() + ") ");
      }
      System.out.println();
      System.out.print("Closed: ");
      for (ScoredVertex v : closedList) {
        System.out.println("(" + v.getX() + "," + v.getY() + " fx: " + v.getFx() + " hx: "
            + v.getHx() + " gx: " + v.getGx() + ") ");
      }
      System.out.printf("\n \n");
    }

    System.out.print("no route");
    throw new RuntimeException("no route");

  }



  public Path returnPath(ScoredVertex start, ScoredVertex dest, Map m) {
    ScoredVertex current = dest;

    routeToStart.add(dest);
    while (!isEquals(current, start)) {
      current = (ScoredVertex) current.parent;
      routeToStart.add((ScoredVertex) current);
    }

    System.out.print("\n" + "Path to take:");
    Collections.reverse(routeToStart);

    Path p = new Path(new Waypoint(dest.getX(), dest.getY()));

    for (Vertex w : routeToStart) {
      p.addNode(new Waypoint(w.getX(), w.getY()));
      System.out.printf("(%d,%d) ", w.getX(), w.getY());
    }
    System.out.printf("\n" + "Total routeToStart length: %d" + "\n", p.getLength());
    return p;
  }



  public boolean isEquals(Vertex a, Vertex b) {
    return (a.getX() == b.getX() && a.getY() == b.getY());
  }


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
