package unitTesting;


import org.junit.Test;
import pathfind.Heuristic;
import pathfind.Node;



public class HeuristicTesting {

	
	
	
	@Test
	public void test() {
		
		Node a = new Node(1, 1, 100);
		Node b = new Node(10, 10, 200000);
		Node c = new Node(55, 100, 200000);

		
		System.out.print("Node a: " + a);
		System.out.print("Node b: " + b);
		System.out.println();

		Heuristic getDifferenceX = new Heuristic();
		double dx = 0;//getDifferenceX.distanceX(a, b);
		System.out.println("dx: " + dx);

		
		Heuristic getDifferenceY = new Heuristic();
		double dy = 0;//getDifferenceY.distanceY(a, b);
		System.out.println("dy: " + dy);
	
	
		System.out.println();
		
		Heuristic testEuclidean = new Heuristic();
		Double resultEuc = 0.0;//testEuclidean.euclideanHeuristic(a, b);
		System.out.println("Euclidean Distance hx: " + resultEuc);
		
		Heuristic testDiagonal = new Heuristic();
		Double resultDiag = 0.0;//testDiagonal.DiagonalHeuristic(a, b);
		System.out.println(" Diagonal Distance hx: " + resultDiag);
		
		Heuristic testManhattan = new Heuristic();
		Double resultMan = 0.0;//testManhattan.manhattanHeuristic(a, b);
		System.out.println("Manhattan Distance hx: " + resultMan);
		
		if(dy == 0.0 && dx > 0.0 || dx == 0.0 && dy > 0.0){
		assert(resultMan.equals(resultEuc));
		}
		else if(a.x == a.y && b.x == b.y && dy % 1 == 0 && dx % 1 == 0){
		assert(resultDiag < resultEuc);
		assert(resultDiag < resultMan);
		}
	}
	
}
