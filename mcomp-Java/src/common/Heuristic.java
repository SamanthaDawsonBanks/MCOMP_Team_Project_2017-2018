package common;

public class Heuristic {
	
	//cost function gives minimum cost of D
	//Simple Case is to set D = 1
	//D2 is the cost of moving diagonally
	//If we set D and D1 = 1 we are using the Chebyshev distance/metric
	//If we set D = 1 and D2 = sqrt(2) we are using the octile distance/metric
	//----TO DO: REFACTOR D/D2----
	double D = 1.5;
	double D2 = 1;
	
	
	//Manhattan Distance Heuristic
	//Takes in start node and goal node (a and b)
	//Find non negative difference in x values and y values between nodes;
	//Calculate hx using Manhattan distance heuristic.
	public Double manhattanHeuristic(Node a, Node b){
		double xDiff = Math.abs(a.x - b.x);
		double yDiff = Math.abs(a.y - b.y);
		
		double hx = D * (xDiff + yDiff);
		return hx;
	}
	
	//Diagonal Distance Heuristic
	//Takes in start node and goal node (a and b)
	//Find non negative difference in x values and y values between nodes;
	//Calculate hx using Diagonal distance heuristic.
	//
	public Double DiagonalHeuristic(Node a, Node b){
		double xDiff = Math.abs(a.x - b.x);
		double yDiff = Math.abs(a.y - b.y);
		
		double hx = ((xDiff + yDiff) + (D2 - 2 * D) * Math.min(xDiff, yDiff));
		return hx;
	}
	
	//Euclidean Distance Heuristic
	//Takes in start node and goal node (a and b)
	//Find non negative difference in x values and y values between nodes;
	//Calculate hx using Euclidean distance heuristic.
	public Double euclideanHeuristic(Node a, Node b){
		double xDiff = Math.abs(a.x - b.x);
		double yDiff = Math.abs(a.y - b.y);
		
		double hx = D * Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
		return hx;
	}
	
	
	public Double distanceX(Node a, Node b){
		double xDiff = Math.abs(a.x - b.x);
		return xDiff;
	}
	
	public Double distanceY(Node a, Node b){
		double yDiff = Math.abs(a.y - b.y);
		return yDiff;
	}

}
