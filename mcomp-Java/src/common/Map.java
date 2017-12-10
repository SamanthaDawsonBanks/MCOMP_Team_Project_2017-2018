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
  
  private MapLayer amalgamatedMap;
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
    amalgamateLayers(layer);
  }

  private void amalgamateLayers(MapLayer layer) {
    // TODO Auto-generated method stub
    for (cell : layer) {
      //add cell info to amalgamatedMap in correct loc
    }
        
  }

  public MapLayer getAmalgamatedMap() {
    return amalgamatedMap;//FIXME possible NPE
    
  }
  
}


