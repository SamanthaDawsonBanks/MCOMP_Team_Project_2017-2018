package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class AStar {

	private Heuristic h = new Heuristic();
	private ArrayList<Vertex> path = new ArrayList<Vertex>();
	private ArrayList<Vertex> openList = new ArrayList<Vertex>();
	private ArrayList<Vertex> closedList = new ArrayList<Vertex>();

	public void pathfind(Waypoint start, Waypoint dest, Map m) {

		PathOptimisation p = new PathOptimisation();
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
				if(v.fx == m.getAmalgamatedMap().getVertex(current).fx) {
					if(v.hx < m.getAmalgamatedMap().getVertex(current).hx) {
						current = temp;
					}
				}
			}

			if(current.getX() == dest.getX() && current.getY() == dest.getY()) {
				System.out.print("goal reached");
				returnPath(start, dest, m);
				p.numOfTurns(path);
				p.shortenPath(path);
				break;
			}


			openList.remove(m.getAmalgamatedMap().getVertex(current));
			closedList.add(m.getAmalgamatedMap().getVertex(current));
			System.out.println("current node: "+ current);


			for(Vertex v: m.getAmalgamatedMap().getVertex(current).edges) {
				if(v == null) {
					continue;
				}

				v.gx = m.getAmalgamatedMap().getVertex(current).gx + 1;
				double childGx = v.getG();
				
				if(childGx < v.getG()) {
					openList.remove(v);
					closedList.remove(v);
				}

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


	public Path returnPath(Waypoint start, Waypoint dest, Map m) {
		Path p = new Path(dest);
		Waypoint current = dest;
		Vertex v = m.getAmalgamatedMap().getVertex(current);     

		path.add(m.getAmalgamatedMap().getVertex(dest));
		while(!v.parente.equals(m.getAmalgamatedMap().getVertex(start))) {
			v = v.parente;
			path.add(v);
		}
		path.add(m.getAmalgamatedMap().getVertex(start));
		System.out.print("\n" + "Path to take:");
		Collections.reverse(path);
		for(Vertex w: path) {
			p.addNode(new Waypoint(w.getX(), w.getY()));
			System.out.printf("(%d,%d) ",w.getX(), w.getY());
		}
		System.out.printf("\n" + "Total path length: %d" + "\n", p.getLength());
		return p;
	}

	public ArrayList<Vertex> getList(){
		return closedList;
	}

	public ArrayList<Vertex> getList2(){
		return path;
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