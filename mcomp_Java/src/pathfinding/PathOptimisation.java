package pathfinding;

import java.util.ArrayList;
import common.datatypes.map.griddedMap.Vertex;

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
    System.out.printf("\n" + "turns in route: %d" ,turnCounter);
    return turnCounter;
  }
  
  public ArrayList<Vertex> shortenPath(ArrayList<Vertex> path){
    System.out.println();
    System.out.print("Shorter Path: ");

    for(int i = 0; i < path.size()-1; i++) {
      Vertex current = path.get(i);
      Vertex next  = path.get(i+1);
      if(i==0) {
        path.add(current);
      }
     
      if(i!=0) {
        Vertex prev = path.get(i-1);
        if(prev.getX() != next.getX() && prev.getY() != next.getY()) {
          ArrayList<Vertex> newPath = new ArrayList<Vertex>();
          newPath.add(current);

          for(Vertex w: newPath) {
            System.out.printf("(%d,%d) ", w.getX(), w.getY());
          } 
        }
      }
    }

    return path;

  }

}
