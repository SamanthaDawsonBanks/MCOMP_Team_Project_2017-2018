package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class AStar {

  private Heuristic h = new Heuristic();

  private ArrayList<ScoredVertex> routeToStart = new ArrayList<ScoredVertex>();
  private ArrayList<ScoredVertex> openList = new ArrayList<ScoredVertex>();
  private ArrayList<ScoredVertex> closedList = new ArrayList<ScoredVertex>();

  public void pathfind(Waypoint start, Waypoint dest, Map m) {

    PathOptimisation p = new PathOptimisation();

    ScoredVertex svStart = new ScoredVertex(m.getAmalgamatedMap().getVertex(dest), m.getAmalgamatedMap());
    ScoredVertex svDest = new ScoredVertex(m.getAmalgamatedMap().getVertex(start), m.getAmalgamatedMap());


    svStart.setGx(0.0);
    svStart.setHx(h.euclideanHeuristic(svStart, svDest));
    svStart.setFx(svStart.getHx());

    openList.add(svStart);

    int counter = 0;
    while (!openList.isEmpty()) {

      ScoredVertex current = svStart;

      if (openList.size() == 0) {
        System.out.print("no route");
        throw new RuntimeException("no route");
      }

      if (openList.contains(svStart)) {
        svStart.setFx(new Heuristic().manhattanHeuristic(svStart, svDest) + svStart.getGx());
      }

      for (ScoredVertex v : openList) {
        if (current == null || v.getFx() < current.getFx()) {
          current = v;
        }
        if (v.getFx() == current.getFx()) {
          if (v.getHx() < current.getHx()) {
            current = v;
          }
        }
      }

      if (isEquals(current, svDest)) {
        System.out.print("goal reached");
        System.out.print(closedList.size());
        returnPath(svStart, svDest, m);
        break;
      }


      openList.remove(current);
      closedList.add(current);
      System.out.println("current node: " + current.getX() + "," + current.getY());
      counter++;


      for (Vertex v : current.edges) {

        if (v == null) {
          continue;
        }

        ScoredVertex z = new ScoredVertex(v, m.getAmalgamatedMap());

        z.setGx(current.getGx() + 1);
        double childGx = z.getGx();

        if (childGx < z.getGx()) {
          openList.remove(z);
          closedList.remove(z);
        }

        if (!myContains(openList, z) && !myContains(closedList, z)
            && !v.equals(m.getAmalgamatedMap().blocked)) {
          z.setGx(childGx);
          z.setHx(h.manhattanHeuristic(z, svDest));
          z.setFx(z.getHx() + z.getGx());
          z.parent = current;
          openList.add(z);
        }
        if (v.equals(m.getAmalgamatedMap().blocked)) {
          System.out.print("");
        } else if (!closedList.contains(z)) {
          System.out.printf("child:(%d,%d)" + " gx = %.1f" + " hx = %.1f" + " fx = %.1f" + "\n",
              z.getX(), z.getY(), z.getGx(), z.getHx(), z.getFx());
        }
      }

      System.out.print("Open: ");
      for (ScoredVertex v : openList) {
        System.out.print("(" + v.getX() + "," + v.getY() + " fx: " + v.getFx() + " hx: " + v.getHx()
            + " gx: " + v.getGx() + ") ");
      }
      System.out.println();
      System.out.print("Closed: ");
      for (Vertex v : closedList) {
        System.out.printf("(%d,%d) ", v.getX(), v.getY());
      }
      System.out.printf("\n \n");
    }
  }


  public Path returnPath(ScoredVertex start, ScoredVertex dest, Map m) {
    ScoredVertex current = dest;

    routeToStart.add(dest);

    while (!isEquals(current, start)) {
      current = (ScoredVertex) current.parent;
      routeToStart.add((ScoredVertex) current);

    }
    routeToStart.add(start);
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
  

  public boolean myContains(ArrayList<ScoredVertex> l, ScoredVertex v) {
    boolean res = false;
    for (ScoredVertex sv : l) {
      if (isEquals(sv, v)) {
        res = true;
      }
    }
    return res;
  }
  
  
}
