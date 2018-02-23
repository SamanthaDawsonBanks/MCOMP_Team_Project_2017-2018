/**
 * 
 */
package common.datatypes.map;

import java.util.ArrayList;
import common.datatypes.Waypoint;
import common.datatypes.map.griddedMap.GridDesign;
import common.datatypes.map.griddedMap.GriddedMap;

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
    if (amalgamatedMap == null) {
      amalgamatedMap = new GriddedMap(layer, GridDesign.TETRA);
    } else {
    for (Waypoint w : layer) {
      //add cell info to amalgamatedMap in correct loc
      amalgamatedMap.add(w);
    }
    }
  }

  public GriddedMap getAmalgamatedMap() {
    return amalgamatedMap;
  }
  
}


