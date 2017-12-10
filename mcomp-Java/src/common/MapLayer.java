/**
 * 
 */
package common;

/**
 * @author David Avery
 *
 */
public class MapLayer {// FIXME adjust for iterator??

  private GridDesign gridDesign;

  /**
   * The default constructor for a MapLayer, with the value (TETRA | 4 | Square) for grid design
   */
  public MapLayer() {
    this.gridDesign = new GridDesign();
  }

  /**
   * @return the gridDesign
   */
  public GridDesign getGridDesign() {
    return gridDesign;
  }


  public void transform(int x, int y, int a) {
    //calc trans and rot
    //call rot
    //call trans
  }
  
  
  private void rotate(int azimuth) {
    //apply rotation by -ve of azimuth to bring map to be 'north' = 'top'
  }


  private void translate(int xOffset, int yOffset) {
    //apply rotation by -ve of azimuth to bring map to be 'north' = 'top'
  }



}
