package pathfinding;

import common.datatypes.map.griddedMap.Vertex;

/**
 * Class which contains all methods for returning the hx (Heuristic cost) between two Vertices. Each
 * method must take in the current Vertex and destination Vertex to work out the estimated cost
 * between these two points. Each method handles a different implementation of calculating the hx
 * value and is called in via the AStar search (current algorithm). *
 * 
 * @author Harry Jackson 14812630
 * 
 * @version 1.0
 * @since 2018-04-20
 * 
 * @see pathfinding.ScoredVertex
 * @see common.datatypes.map.griddedMap.Vertex
 * 
 */
public class Heuristic {


  /**
   * cost function gives minimum cost of D Simple Case is to set D = 1 D2 is the cost of moving
   * diagonally If we set D and D1 = 1 we are using the Chebyshev distance/metric If we set D = 1
   * and D2 = sqrt(2) we are using the octile distance/metric
   */
  double D = 1;
  double D2 = 1.5;



  /**
   * Method for working out the Manhattan distance between two vertices a and b. Find non negative
   * difference in x values and y values between nodes.
   * 
   * @param Vertex a the current Vertex
   * @param Vertex b the destination Vertex
   * 
   * @return hx (Manhattan Heuristic) value
   */
  public Double manhattanHeuristic(Vertex current, Vertex dest) {
    double xDiff = Math.abs(current.getX() - dest.getX());
    double yDiff = Math.abs(current.getY() - dest.getY());

    double hx = D * (xDiff + yDiff);
    return hx;
  }

  /**
   * Method for working out the Diagonal distance between two vertices a and b. Find non negative
   * difference in x values and y values between nodes.
   * 
   * @param Vertex a the current Vertex
   * @param Vertex b the destination Vertex
   * 
   * @return hx (Diagonal Heuristic) value
   */
  public Double DiagonalHeuristic(Vertex a, Vertex b) {
    double xDiff = Math.abs(a.getX() - b.getX());
    double yDiff = Math.abs(a.getY() - b.getY());

    double hx = ((xDiff + yDiff) + (D2 - 2 * D) * Math.min(xDiff, yDiff));
    return hx;
  }


  /**
   * Method for working out the Euclidian distance between two vertices a and b. Find non negative
   * difference in x values and y values between nodes.
   * 
   * @param Vertex a the current Vertex
   * @param Vertex b the destination Vertex
   * 
   * @return hx (Euclidian heuristic) value
   */
  public Double euclideanHeuristic(Vertex a, Vertex b) {
    double xDiff = Math.abs(a.getX() - b.getX());
    double yDiff = Math.abs(a.getY() - b.getY());

    double hx = D * Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    return hx;
  }

  /**
   * Method for favouring straightlines and reducing the number of turns in the Path.
   * 
   * It calculates distance similar to the Manhattan heuristic. However during runtime it checks the
   * direction the robot is facing, and the direction it would be after it moved to the child
   * Vertex.
   * 
   * If the current facing direction equals the neighbours facing direction we score that Vertex
   * better. So if the "robot" is facing north at (2,8), and its children are (3,8) and (2,9). (3,8)
   * would allow the robot to stay facing north and therefore scores this neighbour better.
   * 
   * 
   * @param Vertex current the current Vertex
   * @param Vertex dest the destination Vertex
   * @param ScoredVertex a the current facing ScoredVertex
   * @param ScoredVertex b the adjacent children of current.
   * 
   * @return hx (Straightline heuristic) value
   */
  public Double straightLineHeuristic(Vertex current, Vertex dest, ScoredVertex a, ScoredVertex b) {
    double hx;
    double xDiff = Math.abs(current.getX() - dest.getX());
    double yDiff = Math.abs(current.getY() - dest.getY());

    if (a.getDirection() == b.getDirection()) {
      hx = D * (xDiff + yDiff) - 0.5;
    } else {
      hx = D * (xDiff + yDiff);
    }
    return hx;
  }

  /**
   * Method for comparing two vertices and returning the difference in their X values
   * 
   * @param Vertex a
   * @param Vertex b
   * 
   * @return difference in the X distance.
   */
  public Double distanceX(Vertex a, Vertex b) {
    double xDiff = Math.abs(a.getX() - b.getX());
    return xDiff;
  }

  /**
   * Method for comparing two vertices and returning the difference in their Y values
   * 
   * @param Vertex a
   * @param Vertex b
   * 
   * @return difference in the Y distance.
   */
  public Double distanceY(Vertex a, Vertex b) {
    double yDiff = Math.abs(a.getY() - b.getY());
    return yDiff;
  }


}
