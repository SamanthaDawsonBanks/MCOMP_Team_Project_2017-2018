package common.datatypes.map.griddedmap;

import common.datatypes.Waypoint;

public class Chunk {

  public Chunk() {
    // TODO Auto-generated constructor stub
  }

  private int gridSize = 64;
  private boolean[][] data = new boolean[gridSize][gridSize];

  /**
   * 
   */
  public Chunk(Waypoint w) {
    // TODO Auto-generated constructor stub
    // TODO this is going to need some serious optimisation if accessed on a single point bases
    this.add(w);
  }


  public boolean add(Waypoint w) {
    return false;
    //TODO

  }

  
//  boolean[] listTrue() {//FIXME this is a mess!!!
//    ArrayList<Boolean> a = new ArrayList<Boolean>();
//    for(boolean[] data2: data)
//    {
//        for(boolean b : data2)
//            if (b) {
//              a.add(b);
//            }
//    }
//    int size = a.size();
//    boolean[] temp = new boolean[size];
//    for (int i = 0; i < size; i++) {
//      temp[i] = a.get(i).booleanValue();//FIXME this is useless - OMG step away from the code!!!
//    }
//    return temp;
//
//  }
// and we try again...
  
  
boolean[][] getGrid() {//FIXME what do we want from this DS?
  return data;
}
  
  
  
  
  
  
  
}
