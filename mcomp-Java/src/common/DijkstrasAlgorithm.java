package common;



public class DijkstrasAlgorithm  {
	
	private Waypoint waypoint;
	private Map map;
	Boolean occupied = false;
	private Waypoint endGoal;
	private double g;
	private double fx;
	private double hx;
	
 
	
	public AStarAlgorithm node(double x, double y){
		return null;
	}
	
	public Map FindPath(Map map){
		this.map = map;
		return map;
	}
	
	public double getHx(){
		return hx;
	}
	
	public double getFx(){
		return fx;
	}
	
	public void calcF(){
		this.hx = 0.0;  //Implementation
		this.fx = g + hx;
	}
	
	public double Heuristic(){
		return fx;
		
	}
	
	
	
	}
	
		
	

