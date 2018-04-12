package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.ScoredVertex;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class AStarScoreVertex {

	private Heuristic h = new Heuristic();

	private ArrayList<ScoredVertex> path = new ArrayList<ScoredVertex>();
	private ArrayList<ScoredVertex> openList = new ArrayList<ScoredVertex>();
	private ArrayList<ScoredVertex> closedList = new ArrayList<ScoredVertex>();

	public void pathfind(Waypoint start, Waypoint dest, Map m) {

		PathOptimisation p = new PathOptimisation();

		ScoredVertex s = new ScoredVertex(m.getAmalgamatedMap().getVertex(start), m.getAmalgamatedMap());

		s.setGx(0.0);
		s.setHx(h.euclideanHeuristic(start, dest));
		s.setFx(s.getHx());

		openList.add(s);

		while(!openList.isEmpty()) {

			ScoredVertex current = null;

			if(openList.size()==0) {
				System.out.print("no route");
				throw new RuntimeException("no route");
			}

			if(openList.contains(s)) {
				s.calcF(start, dest);
			}

			for(ScoredVertex v: openList) {				
				if(current == null || v.getFx() <  current.getFx()) {
					current = v;
				}
				if(v.getFx() == current.getFx()) {
					if(v.getHx() < current.getHx()) {
						current = v;
					}
				}
			}

			if(current.getX() == dest.getX() && current.getY() == dest.getY()) {
				System.out.print("goal reached");
				returnPath(start, dest, m);
				break;
			}


			openList.remove(current);
			closedList.add(current);
			System.out.println("current node: " + current.getX() + "," + current.getY());


			for(Vertex v: current.edges) {

				ScoredVertex z = new ScoredVertex(v, m.getAmalgamatedMap());

				if(v == null) {
					continue;
				}

				z.setGx(current.getGx() + 1);
				double childGx = z.getGx();

				if(childGx < z.getGx()) {
					openList.remove(z);
					closedList.remove(z);
				}
				if(!openList.contains(z) && !closedList.contains(z) && !v.equals(m.getAmalgamatedMap().blocked)) {
					z.setGx(childGx);
					z.setHx(h.manhattanHeuristic(new Waypoint(v.getX(), v.getY()), dest));
					z.setFx(z.getHx() + z.getGx());
					z.parent = current;
					openList.add(z);
				}
				if(v.equals(m.getAmalgamatedMap().blocked)) {
					System.out.print("");
				}else if(!closedList.contains(v)) {
					System.out.printf("child:(%d,%d)" + " gx = %.1f" + " hx = %.1f" + " fx = %.1f" +  "\n", v.getX(), v.getY(), z.getGx(), z.getHx(), z.getFx());
				}
			}

			System.out.print("Open: ");
			for(ScoredVertex v: openList) {
				System.out.print("(" + v.getX() + "," + v.getY() + " fx: " + v.getFx() + " hx: " + v.getHx() + " gx: "+ v.getGx() + ") ");
			}
			System.out.println();
			System.out.print("Closed: ");
			for(Vertex v: closedList) {
				System.out.printf("(%d,%d) ",v.getX(), v.getY());
			}
			System.out.printf("\n \n");
		}
	}


	public Path returnPath(Waypoint start, Waypoint dest, Map m) {
		Path p = new Path(dest);
		Waypoint current = dest;
		ScoredVertex v = (ScoredVertex) m.getAmalgamatedMap().getVertex(current);     

		path.add((ScoredVertex) m.getAmalgamatedMap().getVertex(dest));
		while(!v.parent.equals(m.getAmalgamatedMap().getVertex(start))) {
			v = (ScoredVertex) v.parent;
			path.add(v);
		}
		path.add((ScoredVertex) m.getAmalgamatedMap().getVertex(start));
		System.out.print("\n" + "Path to take:");
		Collections.reverse(path);
		for(Vertex w: path) {
			p.addNode(new Waypoint(w.getX(), w.getY()));
			System.out.printf("(%d,%d) ",w.getX(), w.getY());
		}
		System.out.printf("\n" + "Total path length: %d" + "\n", p.getLength());
		return p;
	}

	public ArrayList<Waypoint> ToArray(ArrayList<Vertex> l) {
		ArrayList<Waypoint> w = new ArrayList<Waypoint>();
		for(Vertex v: l) {
			w.add(new Waypoint(v.getX(), v.getY()));
		}
		return w;
	}

	public boolean developerMode() {
		return false;
	}
}