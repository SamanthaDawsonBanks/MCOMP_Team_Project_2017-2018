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

  private ArrayList<MapLayer> layers = new ArrayList<MapLayer>();
  private GriddedMap amalgamatedMap;
  private int gridSize;

  /**
   * 
   */
  public Map(int gridSize, MapLayer layer) {
    // TODO Auto-generated constructor stub
    this.gridSize = gridSize;
    this.addLayer(layer);
  }

  /**
   * @return the layers
   */
  public MapLayer getLayer(int index) {
    return layers.get(index);// FIXME possible IOOB
  }

  /**
   * @param layers the layers to set
   */
  public void addLayer(MapLayer layer) {
    // transform layer??
    layers.add(layer);
    amalgamateLayer(layer);
  }

  private void amalgamateLayer(MapLayer layer) {
    // TODO Auto-generated method stub
    if (amalgamatedMap == null) {
      amalgamatedMap = new GriddedMap(GridDesign.TETRA, gridSize, this);
    }
    for (Waypoint w : layer.addOpens()) {
      // add cell info to amalgamatedMap in correct loc
      amalgamatedMap.add(w);
    }
  }

  public GriddedMap getAmalgamatedMap() {
    return amalgamatedMap;
  }

}


