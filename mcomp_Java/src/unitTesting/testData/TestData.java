/**
 * 
 */
package unitTesting.testData;

import java.util.ArrayList;
import common.datatypes.Waypoint;

/**
 * @author David Avery 15823926
 * @author Harry Jackson 14812630
 *
 */
public class TestData {

  public static ArrayList<Waypoint> getPresentationMaze() {

    ArrayList<int[]> ys = new ArrayList<int[]>();
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    ys.add(new int[] { 1,                                 13,   15}); 
    ys.add(new int[] { 1,    3, 4, 5, 6, 7, 8, 9,10,11,   13,   15}); 
    ys.add(new int[] { 1,    3,          7,         11,   13,   15}); 
    ys.add(new int[] { 1,    3,    5,    7,    9,   11,   13,   15}); 
    ys.add(new int[] { 1,    3,    5,          9,   11,   13,   15}); 
    ys.add(new int[] { 1,    3,    5, 6, 7, 8, 9,   11,12,13,   15}); 
    ys.add(new int[] { 1,                      9,               15}); 
    ys.add(new int[] { 1,    3, 4, 5, 6, 7,    9,10,11,12,13,   15}); 
    ys.add(new int[] { 1,                           11,         15}); 
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    
    int y = 1;
    for (int[] line : ys) {
      for (int x : line) {
        l.add(new Waypoint(x,y));
      }
      y++;
    }

    return l;

  }

  public static ArrayList<Waypoint> getEmptyMaze() {

    ArrayList<int[]> ys = new ArrayList<int[]>();
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    
    int y = 1;
    for (int[] line : ys) {
      for (int x : line) {
        l.add(new Waypoint(x,y));
      }
      y++;
    }

    return l;

  }

  public static ArrayList<Waypoint> getSteppedMaze() {

    ArrayList<int[]> ys = new ArrayList<int[]>();
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1,                                 13,   15}); 
    ys.add(new int[] { 1,                              12,      15}); 
    ys.add(new int[] { 1,                           11,         15}); 
    ys.add(new int[] { 1,                        10,            15}); 
    ys.add(new int[] { 1,                      9,               15}); 
    ys.add(new int[] { 1,                   8,                  15}); 
    ys.add(new int[] { 1,                7,                     15}); 
    ys.add(new int[] { 1,             6,                        15}); 
    ys.add(new int[] { 1,          5,                           15}); 
    ys.add(new int[] { 1,       4,                              15}); 
    ys.add(new int[] { 1,    3,                                 15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    
    int y = 1;
    for (int[] line : ys) {
      for (int x : line) {
        l.add(new Waypoint(x,y));
      }
      y++;
    }

    return l;

  }

  public static ArrayList<Waypoint> getmaze2RealTestHeuristicPathOptimisation() {

    ArrayList<int[]> ys = new ArrayList<int[]>();
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    ys.add(new int[] { 1,                   8,                  15}); 
    ys.add(new int[] { 1, 2,    4,    6,         10,      13,   15}); 
    ys.add(new int[] { 1, 2,    4, 5, 6, 7, 8, 9,10,11,   13,   15}); 
    ys.add(new int[] { 1, 2,    4,                        13,   15}); 
    ys.add(new int[] { 1,                7, 8, 9,10,11,12,13,   15}); 
    ys.add(new int[] { 1, 2, 3, 4,    6, 7,               13,   15}); 
    ys.add(new int[] { 1, 2, 3,                     11,12,13,   15}); 
    ys.add(new int[] { 1, 2,       5, 6, 7, 8, 9,10,11,12,13,   15}); 
    ys.add(new int[] { 1,                                       15}); 
    ys.add(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15}); 
    
    ArrayList<Waypoint> l = new ArrayList<Waypoint>();
    
    int y = 1;
    for (int[] line : ys) {
      for (int x : line) {
        l.add(new Waypoint(x,y));
      }
      y++;
    }

    return l;

  }


}
