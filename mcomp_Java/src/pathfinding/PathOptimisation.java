package pathfinding;

import java.util.ArrayList;

import common.datatypes.Waypoint;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;

public class PathOptimisation {

	public int numOfTurns(ArrayList<Vertex> path) {
		int counter = 2;
		int turnCounter = 0;
		for(Vertex v: path) {
			if(counter < path.size()) {
				Vertex future = path.get(counter);
				if(future.getX() != v.getX() && future.getY() != v.getY()) {
					turnCounter++;
				}
				counter++;
			}
		}
		System.out.printf("Turns in route: %d" + "\n" ,turnCounter);
		return turnCounter;
	}

	public ArrayList<Vertex> shortenPath(ArrayList<Vertex> path){
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
		System.out.print("New path to take: ");
		for(Vertex v: newPath) {
			System.out.printf("(%d,%d) ",v.getX(), v.getY());
		}
		System.out.printf("\n" + "New total path length: " + "%d", p.getLength());
		return newPath;
	}

	public void getDirection(Waypoint current, Waypoint next) {
		if(next.getX() - current.getX() > 0) {
			System.out.print("move east -- ");
		}
		if(next.getX() - current.getX() < 0) {
			System.out.print("move west -- ");
		}
		if(next.getY() - current.getY() < 0) {
			System.out.print("move south - ");
		}
		if(next.getY() - current.getY() > 0) {
			System.out.print("move north - ");
		}
	}
}