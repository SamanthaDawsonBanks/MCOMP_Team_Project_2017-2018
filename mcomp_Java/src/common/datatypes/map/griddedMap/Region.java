/**
 * 
 */
package common.datatypes.map.griddedMap;

import common.datatypes.Waypoint;

/**
 * @author David Avery
 *
 */
public class Region {

  private Chunk[][] data = new Chunk[GriddedMap.gridSize][GriddedMap.gridSize];

  /**
   * 
   */
  public Region(Waypoint w) {
    // TODO Auto-generated constructor stub
    // TODO this is going to need some serious optimisation if accessed on a single point bases
    this.add(w);
  }


  public boolean add(Waypoint w) {
    // TODO
    int ChunkX = (int) ((w.getX() / (GriddedMap.gridSize ^ 1)) % GriddedMap.gridSize);
    int ChunkY = (int) ((w.getY() / (GriddedMap.gridSize ^ 1)) % GriddedMap.gridSize);
    if (data[ChunkX][ChunkY] == null) {
      data[ChunkX][ChunkY] = new Chunk(w);
    } else {
      data[ChunkX][ChunkY].add(w);
    }

    // calc which chunk
    // call add on calced chunk
    // rest of call in chunk
    return true;

  }


  // boolean[] listTrue() {//FIXME this is a mess!!!
  // ArrayList<Boolean> a = new ArrayList<Boolean>();
  // for(boolean[] data2: data)
  // {
  // for(boolean b : data2)
  // if (b) {
  // a.add(b);
  // }
  // }
  // int size = a.size();
  // boolean[] temp = new boolean[size];
  // for (int i = 0; i < size; i++) {
  // temp[i] = a.get(i).booleanValue();//FIXME this is useless - OMG step away from the code!!!
  // }
  // return temp;
  //
  // }
  // and we try again...


  Chunk[][] getGrid() {// FIXME what do we want from this DS?
    return data;
  }



}
