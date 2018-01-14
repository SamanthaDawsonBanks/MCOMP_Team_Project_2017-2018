package common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import javax.xml.soap.Node;


public class AStarAlgorithm { 
		
		//Take in map from map class
	  	private int[][] Map;
	  
	  	//true/false check
	  	boolean occupied = false;
	  
	  	
	 
	  	
	  	//initialise scoring functionality
	  	//fx = total estimated cost of path through node n
	  	double fx = 0.0;
	  	//gx = cost so far to reach node n
	  	double gx = 0.0;
	  	//hx = estimated cost from n to goal
	  	double hx;
	  	
	  	//initialise new arrays for checking nodes	 
	  	//OpenSet = Nodes not yet evaluated.
	  	ArrayList<String> OpenSet = new ArrayList<String>();
	  	//ClosedSet = Nodes already evaluated.
	  	ArrayList<String> ClosedSet = new ArrayList<String>();
	  	
	  	//initialise start Waypoint.
	 	//initialise End Waypoint.
	  	Waypoint startWaypoint;
	  	Waypoint endWaypoint;
	  	Node start;
	  	Node goal;
	  	Node current;
	 
	  	
	  	//Gui connection for setting goal
	  	//TO DO -- implement --
	  	public Node setGoal(Node goal){
			return goal;	
		}
	  	
	  	public Node getCurrent(){
	  		return current;
	  	}
	  	
	  	public void setCurrent(Node current){
	  		this.current = current;
	  	}
	  	
	  	
	  	
	  	//Check if current node is the target goal node
	  	//if not return and continue search
	  	public boolean goalReached(Node goal){
	  		if(current == goal){
	  			return true;
	  		}
	  		else {
	  			return false;
	  		}
	  	}
	  	
	  	public String checkNode(Node current){
	  	 	return current.toString();
	  	}
	  	
}
	  	
	
	  	
	  	
	  		

	  		
	  	
	  	
	  	
	  	




	

