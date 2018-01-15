package common;


public class Node {

		public int y;
		public int x;
		int cost;
		public Node nodeLocation;
		public String value;
		private final int id;
		
		
		/*Create a node object that takes in two values
		 * x and y in its argument.
		 */
		public Node(int x, int y, int id){
			this.x = x;
			this.y = y;
			this.id = id;
		}
	
		public int nodeID(){
			return id;
		}
		
		public int NodeY(){
			return y;
		}
		
		public int NodeX(){
			return x;
		}
		
		/*Set Location of node*/
		public void setLocation(Node nodeLocation){
			this.nodeLocation = nodeLocation;
		}
		
		
		//TO DO -- MOVE OUT
		//Retrieve node location
		public Node getLocation(){
			return nodeLocation;
		}
		
		/*Comparison between nodes and their associated
		  Cost. returns values to be queried.*/
		public int Compare(Node nodeA, Node nodeB){
			if(nodeA.cost < nodeB.cost){
				return -1;
			}
			if(nodeA.cost > nodeB.cost){
				return 1;
			}
			else {
				return 0;
			}
		}
		
		public String toString(){
				return "(" + x + ", " + y + "," + id + " ) ";
			} 
		
		}
		
		