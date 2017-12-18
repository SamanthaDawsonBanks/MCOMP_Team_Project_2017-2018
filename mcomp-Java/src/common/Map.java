/**
 * 
 */
package common;

import java.util.ArrayList;

/**
 * @author David Avery
 *
 */
public class Map {
  
  private GriddedMap amalgamatedMap;
  private ArrayList<MapLayer> layers = new ArrayList<MapLayer>();

  /**
   * 
   */
  public Map(MapLayer layer) {
    // TODO Auto-generated constructor stub
    this.addLayer(layer);
  }

  /**
   * @return the layers
   */
  public MapLayer getLayer(int index) {
    return layers.get(index);//FIXME possible IOOB
  }

  /**
   * @param layers the layers to set
   */
  public void addLayer(MapLayer layer) {
    //transform layer??
    layers.add(layer);
    amalgamateLayer(layer);
  }

  private void amalgamateLayer(MapLayer layer) {
    // TODO Auto-generated method stub
    for (Waypoint w : layer) {
      //add cell info to amalgamatedMap in correct loc
      amalgamatedMap.add(w);
    }
        
  }

  public GriddedMap getAmalgamatedMap() {
    return amalgamatedMap;
  }
  
}


