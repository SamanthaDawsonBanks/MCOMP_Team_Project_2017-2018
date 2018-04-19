package common.objects;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.MapLayer;
import common.datatypes.map.griddedMap.GriddedMap;
import common.datatypes.map.griddedMap.Region;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;
import common.interfaces.RemoteLeader;
import common.interfaces.RemoteView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import leader.LeaderMain;
import pathfinding.AStar;
import pathfinding.PathOptimisation;
import unitTesting.testData.*;


/**
 * 
 * @author Harry Jackson: 14812630.
 * @author David Avery 15823926
 * 
 * @version 1.0
 * @since 2018-04-11
 * 
 */
public class View extends Application implements RemoteView {
  
  private static final Logger LOGGER = Logger.getLogger(View.class.getName());

  private RemoteLeader localLeaderRef = null;
  private Herd localHerdData;

  private Pane pane;
  private HBox hbox;
  private Label label;
  private int counter = 0;
  private Group circleGroup, rectangleGroup, lineGroup, amalgamateGroup, pathGroup, searchedGroup,
      optimizedGroup;


  /**
   * Takes in all methods that deal with drawing to the GUI and adds them to the new HBox. Adds the
   * HBox to a new pane. Finally the pane is displayed as a new scene for the current stage.
   * 
   * @param Stage
   *
   */
  @Override
  public void start(Stage primaryStage) throws Exception {


    // rmi connect stuff
    localLeaderRef = connectRMI();
    if (localLeaderRef == null) {
      throw new RuntimeException("Unable to connect to Leader");
      //FIXME needs some form of error box saying unable to connect or whatever?
    }
    localHerdData = localLeaderRef.getState();


    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    primaryStage.setX(screenBounds.getMinX());
    primaryStage.setY(screenBounds.getMinY());
    primaryStage.setWidth(screenBounds.getWidth());
    primaryStage.setHeight(screenBounds.getHeight());


    hbox = new HBox();
    hbox.setSpacing(8);
    hbox.getChildren().addAll(getMapBox(), getVBox());
    pane = new Pane();
    pane.getChildren().add(hbox);
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Creates the main box for displaying all data. Has onClick for setting destination.
   * 
   * @return main Rectangle node to display all data.
   * 
   */
  public Rectangle getMapBox() {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    Rectangle r = new Rectangle();
    r.setX(screenBounds.getMinX());
    r.setY(screenBounds.getMinY());
    r.setWidth(screenBounds.getWidth() / 1.30);
    r.setHeight(screenBounds.getHeight() - 50);
    r.setFill(Color.TRANSPARENT);
    r.setStroke(Color.BLACK);
    r.setStrokeWidth(4);
    r.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (counter == 0) {
          System.out.print(event.getSceneX() + "," + event.getSceneY());
          Waypoint w = new Waypoint(event.getSceneX(), event.getSceneY());
          try {
            localLeaderRef.setDestination(w);
          } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          Circle dest = new Circle();
          dest.setCenterX(event.getSceneX());
          dest.setCenterY(event.getSceneY());
          dest.setRadius(5d);
          dest.setFill(Color.RED);
          Label label = new Label("(" + event.getSceneX() + "," + event.getSceneY() + ")");
          label.setLayoutX(event.getSceneX());
          label.setLayoutY(event.getSceneY());
          pane.getChildren().addAll(dest, label);
          counter++;
        } else {
          System.out.print(event.getSceneX() + "," + event.getSceneY());
        }
      }
    });
    return r;
  }

  /**
   * Takes in all current VBox's (getVBoxMap, getVBoxPath ad getAbilities). Adds all to a new VBox
   * 
   * @return List of VBox's
   * 
   */
  public VBox getVBox() {
    VBox vbox = new VBox();
    vbox.setSpacing(18);

    ObservableList<Node> list = vbox.getChildren();
    list.addAll(getVBoxMap(), getVBoxPath(), getAbilities());
    return vbox;
  }


  /**
   * Handles all GUI nodes to do with Map data, sets their size, position and CSS. Used for
   * displaying Map data on GUI.
   * 
   * @see common.datatypes.map.Map
   * @see common.datatypes.map.griddedMap.Vertex
   * @see common.datatypes.map.griddedMap.GriddedMap
   *
   * 
   * @return VBox for displaying all nodes associated with Map data.
   * 
   */
  public VBox getVBoxMap() {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final VBox vbox = new VBox();
    final HBox hboxBtn = new HBox();
    final HBox hboxBtn2 = new HBox();

    label = new Label("Map");
    label.setMaxWidth((screenBounds.getWidth() - getMapBox().getWidth() - 10));
    label.setMinHeight(screenBounds.getHeight() / 10);
    toggleOffStyle(label);

    vbox.setPrefWidth((screenBounds.getWidth() - getMapBox().getWidth() - 10) / 2);
    vbox.setPrefHeight(screenBounds.getHeight() / 3.5);

    Spinner<Integer> spinner = new Spinner<Integer>();
    SpinnerValueFactory<Integer> value = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
    spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
    spinner
        .setStyle("-fx-body-color:#00bfff;" + "-fx-font-size: 15px; " + "-fx-font-weight: bold;");

    spinner.setMaxHeight(vbox.getPrefHeight() / 4);
    spinner.setMaxWidth(vbox.getPrefWidth() / 10);
    spinner.setValueFactory(value);

    /**
     * Button for iteratively displaying Waypoints from LiDar return.
     */
    ToggleButton lidarBtn = new ToggleButton("Lidar Overlay");
    lidarBtn.setMinWidth(vbox.getPrefWidth() - 60);
    lidarBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(lidarBtn);
    lidarBtn.setOnAction(event -> {
      if (lidarBtn.isSelected()) {
        toggleOnStyle(lidarBtn);
        pane.getChildren()
            .add(drawCircle(localHerdData.map.getLayer(spinner.getValue()).getWaypoints()));
      } else {
        toggleOffStyle(lidarBtn);
        pane.getChildren().remove(circleGroup);
      }
    });

    /**
     * Button for displaying Blocked Vertices.
     */
    ToggleButton blockedBtn = new ToggleButton("Blocked Overlay");
    blockedBtn.setMinWidth(vbox.getPrefWidth() - 20);
    blockedBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(blockedBtn);
    blockedBtn.setOnAction(event -> {
      if (blockedBtn.isSelected()) {
        toggleOnStyle(blockedBtn);
        // pane.getChildren().add(drawBlockedVertices(parent.getLocalHerdData().getMap().getAmalgamatedMap().getGrid()));
      } else {
        toggleOffStyle(blockedBtn);
        pane.getChildren().remove(rectangleGroup);
      }
    });

    /**
     * Button for displaying Grid overlay.
     */
    ToggleButton gridBtn = new ToggleButton("Grid Overlay");
    gridBtn.setMinWidth(vbox.getPrefWidth() - 20);
    gridBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(gridBtn);
    gridBtn.setOnAction(event -> {
      if (blockedBtn.isSelected()) {
        toggleOnStyle(gridBtn);
        pane.getChildren().add(drawGrid(scale(TestData.getPresentationMaze(), 40)));
      } else {
        toggleOffStyle(gridBtn);
        pane.getChildren().remove(lineGroup);
      }
    });

    /**
     * Button for displaying Amalgamated Map.
     */
    ToggleButton mapBtn = new ToggleButton("Map Overlay");
    mapBtn.setMinWidth(vbox.getPrefWidth() - 20);
    mapBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(mapBtn);
    mapBtn.setOnAction(event -> {
      if (mapBtn.isSelected()) {
        toggleOnStyle(mapBtn);
        pane.getChildren().add(drawAmalgamatedMap(localHerdData.map.getAmalgamatedMap()));
      } else {
        toggleOffStyle(mapBtn);
        pane.getChildren().remove(amalgamateGroup);
      }
    });

    vbox.setSpacing(9);

    hboxBtn.getChildren().addAll(lidarBtn, spinner, mapBtn);
    hboxBtn.setSpacing(9);

    hboxBtn2.getChildren().addAll(blockedBtn, gridBtn);
    hboxBtn2.setSpacing(9);

    ObservableList<Node> list = vbox.getChildren();
    list.addAll(label, hboxBtn, hboxBtn2);

    return vbox;
  }

  /**
   * Handles all GUI nodes to do with Pathfinding, sets their size, position and CSS. Used for
   * displaying Pathfinding data on GUI.
   * 
   * @see pathfinding.AStar
   * @see pathfinding.Heuristic
   * @see pathfinding.PathOptimisation
   *
   * 
   * @return VBox for displaying all nodes associated with Pathfinding and Heuristics.
   * 
   */
  public VBox getVBoxPath() {

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final VBox vbox = new VBox();
    final HBox hboxBtn = new HBox();
    final HBox hboxBtn2 = new HBox();

    label = new Label("Pathfinding");
    label.setMaxWidth((screenBounds.getWidth() - getMapBox().getWidth() - 10));
    label.setMinHeight(screenBounds.getHeight() / 10);
    toggleOffStyle(label);

    vbox.setPrefWidth((screenBounds.getWidth() - getMapBox().getWidth() - 10) / 2);
    vbox.setPrefHeight(screenBounds.getHeight() / 3.5);

    /**
     * Button for displaying Path retrieved from AStar search.
     */
    ToggleButton pathBtn = new ToggleButton("Path");
    pathBtn.setMinWidth(vbox.getPrefWidth() - 20);
    pathBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(pathBtn);
    pathBtn.setOnAction(event -> {
      if (pathBtn.isSelected()) {
        pane.getChildren().add(drawPath(pathToVertexSet(localHerdData.unoptimizedPath)));
        toggleOnStyle(pathBtn);
      } else {
        toggleOffStyle(pathBtn);
        pane.getChildren().remove(pathGroup);
      }
    });

    /**
     * Button for displaying all searched nodes from AStar search.
     */
    ToggleButton searchedBtn = new ToggleButton("Searched");
    searchedBtn.setMinWidth(vbox.getPrefWidth() - 20);
    searchedBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(searchedBtn);
    searchedBtn.setOnAction(event -> {
      if (searchedBtn.isSelected()) {
        toggleOnStyle(searchedBtn);
        pane.getChildren().add(drawSearched(localHerdData.searchedNodes));
      } else {
        toggleOffStyle(searchedBtn);
        pane.getChildren().remove(searchedGroup);
      }
    });

    /**
     * Button for displaying Optimized path.
     */
    ToggleButton optimizedPathBtn = new ToggleButton("Optimized Path");
    optimizedPathBtn.setMinWidth(vbox.getPrefWidth() - 20);
    optimizedPathBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(optimizedPathBtn);
    optimizedPathBtn.setOnAction(event -> {
      if (optimizedPathBtn.isSelected()) {
        toggleOnStyle(optimizedPathBtn);
        pane.getChildren().add(drawOptimizedPath(pathToVertexSet(localHerdData.optimizedPath)));
      } else {
        toggleOffStyle(optimizedPathBtn);
        pane.getChildren().remove(optimizedGroup);
      }
    });

    /**
     * Button for displaying specialised Heuristic.
     */
    ToggleButton heuristicBtn = new ToggleButton("Heuristic");
    heuristicBtn.setMinWidth(vbox.getPrefWidth() - 20);
    heuristicBtn.setMinHeight(vbox.getPrefHeight() / 4);
    toggleOffStyle(heuristicBtn);
    heuristicBtn.setOnAction(event -> {
      if (heuristicBtn.isSelected()) {
        toggleOnStyle(heuristicBtn);
      } else {
        toggleOffStyle(heuristicBtn);
      }
    });

    vbox.setSpacing(9);

    hboxBtn.getChildren().addAll(pathBtn, optimizedPathBtn);
    hboxBtn.setSpacing(9);

    hboxBtn2.getChildren().addAll(searchedBtn, heuristicBtn);
    hboxBtn2.setSpacing(9);

    ObservableList<Node> list = vbox.getChildren();
    list.addAll(label, hboxBtn, hboxBtn2);

    return vbox;
  }

  /**
   * Handles all GUI nodes to do with Members abilities within a Herd. Used for displaying herd
   * Abilities on GUI.
   * 
   * @see member.MemberMain
   * 
   * @return VBox Layout.
   * 
   */
  public VBox getAbilities() {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final VBox vbox = new VBox();

    label = new Label("Herd Abilities");
    label.setMaxWidth((screenBounds.getWidth() - getMapBox().getWidth() - 10));
    label.setMinHeight(screenBounds.getHeight() / 10);
    toggleOffStyle(label);

    vbox.setPrefWidth((screenBounds.getWidth() - getMapBox().getWidth() - 10) / 2);
    vbox.setPrefHeight(screenBounds.getHeight() / 3.5);
    vbox.setSpacing(-4);

    Rectangle r = new Rectangle();
    r.setWidth((screenBounds.getWidth() - getMapBox().getWidth() - 45));
    r.setHeight((getMapBox().getHeight() / 5) + 20);
    r.setStroke(Color.BLACK);
    r.setStrokeWidth(4);
    r.setFill(Color.WHITE);

    ObservableList<Node> list = vbox.getChildren();
    list.addAll(label, r);

    return vbox;
  }


  /**
   * Takes in a Node (Button, Label, ToggleButton etc) and applies CSS to it depending on what Node
   * type it is.
   * 
   * @param the Node to apply CSS to.
   * 
   * @return styled Node.
   * 
   */
  public Node toggleOffStyle(Node o) {
    if (o.equals(label)) {
      o.setStyle(
          "-fx-background-color:#00bfff;" + "-fx-border-color: #000000;" + "-fx-border-width: 4px;"
              + "-fx-font-size: 25px; " + "-fx-font-weight: bold;" + "-fx-alignment:center");
      return o;
    } else {
      o.setStyle("-fx-background-color:#fff;" + "-fx-border-color: #000000;"
          + "-fx-border-width: 4px;" + "-fx-font-size: 15px; " + "-fx-font-weight: bold;");
      return o;
    }
  }

  /**
   * Takes in a Node (Button, Label, ToggleButton etc) and applies CSS to it.
   * 
   * @param the Node to apply CSS to.
   * 
   * @return styled Node.
   * 
   */
  public Node toggleOnStyle(Node o) {
    o.setStyle("-fx-background-color:#00cc00;" + "-fx-border-color: #000000;"
        + "-fx-border-width: 4px;" + "-fx-font-size: 15px; " + "-fx-font-weight: bold;");
    return o;
  }

  /**
   * Takes in a ArrayList of Waypoints (LiDar return) and draws Circles at their (x,y) position.
   * 
   * @see common.datatypes.Waypoint
   *
   * @param the Collection of Waypoints to be drawn.
   * 
   * @return a group of Circle nodes to display all objects from Lidar return.
   * 
   */
  public Group drawCircle(ArrayList<Waypoint> l) {
    circleGroup = new Group();
    for (Waypoint w : l) {
      Circle circle = new Circle();
      circle.setCenterX(w.getX());
      circle.setCenterY(w.getY());
      circle.setRadius(5d);
      circleGroup.getChildren().add(circle);
    }

    return circleGroup;
  }

  /**
   * Takes in a single Waypoint and draws a Rectangle at its (x,y) location.
   * 
   * @see common.datatypes.Waypoint.
   *
   * @param current Waypoint.
   * 
   * @return a single Rectangle node.
   * 
   */
  public Rectangle drawRectangle(Vertex v) {
    Rectangle rectangle = new Rectangle();
    rectangle.setX(v.getX() - 20);
    rectangle.setY(v.getY() - 20);
    rectangle.setWidth(40);
    rectangle.setStroke(Color.BLACK);
    rectangle.setStrokeWidth(4);
    rectangle.setHeight(40);
    return rectangle;
  }

  /**
   * Takes in a ArrayList of Vertices and draws Rectangles at their (x,y) position.
   * 
   * @see common.datatypes.Waypoint
   *
   * @param the Collection of Waypoints to be drawn.
   * 
   * @return a group of Rectangle nodes to display all Vertices in the Map that are Blocked.
   * 
   */
  public Group drawBlockedVertices(ArrayList<Vertex> l) {
    rectangleGroup = new Group();
    for (Vertex v : l) {
      Rectangle rectangle = drawRectangle(v);
      rectangle.setFill(Color.RED);
      rectangleGroup.getChildren().add(rectangle);
    }

    return rectangleGroup;
  }

  /**
   * Takes in a ArrayList of Vertices and draws Rectangles at their (x,y) position.
   * 
   * @see common.datatypes.Waypoint
   *
   * @param the Collection of Waypoints to be drawn.
   * 
   * @return a group of Rectangle nodes to display all Vertices in the Map that are Blocked.
   * 
   */
  public Group drawAmalgamatedMap(GriddedMap griddedMap) {
    Group amalgamateGroup = new Group();
    for (Vertex v : griddedMap.toArrayList()) {
      Rectangle rectangle = drawRectangle(v);
      rectangle.setFill(Color.RED);
      amalgamateGroup.getChildren().add(rectangle);
    }

    return amalgamateGroup;
  }


  /**
   * Takes in a ArrayList (path item) of Waypoints and draws Rectangles at their (x,y) position.
   * 
   * @see common.datatypes.Waypoint
   * @see pathfinding.AStar
   * 
   * @param the Collection of Waypoints to be drawn.
   * 
   * @return a group of Rectangle nodes to display the initial path the AStar Algorithm found.
   * 
   */
  public Group drawPath(ArrayList<Vertex> vs) {
    pathGroup = new Group();
    for (Vertex v : vs) {
      Rectangle rectangle = drawRectangle(v);
      rectangle.setFill(Color.YELLOW);
      pathGroup.getChildren().add(rectangle);
    }

    return pathGroup;
  }

  /**
   * Takes in a ArrayList (closedList) of Waypoints and draws Rectangles at their (x,y) position.
   * 
   * @see common.datatypes.Waypoint
   *
   * @param the Collection of Waypoints to be drawn.
   * 
   * @return a group of Rectangle nodes to display all Waypoints the AStar Algorithm searched.
   * 
   */
  public Group drawSearched(ArrayList<Vertex> l) {
    searchedGroup = new Group();
    for (Vertex v : l) {
      Rectangle rectangle = drawRectangle(v);
      rectangle.setFill(Color.PURPLE);
      searchedGroup.getChildren().add(rectangle);
    }

    return searchedGroup;
  }

  /**
   * Takes in a ArrayList (optimised path) of Waypoints and draws Rectangles at their (x,y)
   * position.
   * 
   * @see common.datatypes.Waypoint
   *
   * @param the Collection of Waypoints to be drawn.
   * 
   * @return a group of Rectangle nodes to display the optimised path.
   * 
   */
  public Group drawOptimizedPath(ArrayList<Vertex> l) {
    optimizedGroup = new Group();
    for (Vertex v : l) {
      Rectangle rectangle = drawRectangle(v);
      rectangle.setFill(Color.YELLOWGREEN);
      optimizedGroup.getChildren().add(rectangle);
    }

    return optimizedGroup;
  }

  /**
   * Takes in a array of Waypoints and draws vertical and horizontal lines at the Waypoint position.
   * The lines fill the total map rectangle region.
   * 
   * @see common.datatypes.Waypoint
   *
   * @param the Collection of Waypoints to be drawn.
   * 
   * @return a group of line Nodes to display the grid overlay.
   * 
   */
  public Group drawGrid(ArrayList<Waypoint> l) {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    Rectangle r = new Rectangle();
    r.setX(screenBounds.getMinX());
    r.setY(screenBounds.getMinY());
    r.setWidth(screenBounds.getWidth() / 1.3);
    r.setHeight(screenBounds.getHeight() - 50);
    lineGroup = new Group();
    for (int x = 0; x < r.getWidth(); x = x + 40) {
      Line line = new Line();
      line.setStartX(x - 20);
      line.setEndX(x - 20);
      line.setStartY(0);
      line.setEndY(r.getHeight());
      lineGroup.getChildren().add(line);
    }

    for (int y = 0; y < r.getHeight(); y = y + 40) {
      Line line = new Line();
      line.setStartX(0);
      line.setEndX(r.getWidth());
      line.setStartY(y - 20);
      line.setEndY(y - 20);
      lineGroup.getChildren().add(line);
    }

    return lineGroup;
  }
  
  
  private ArrayList<Vertex> pathToVertexSet(Path p) {
    ArrayList<Vertex> res = new ArrayList<Vertex>();
    for (Waypoint w : p.toArray()) {
      res.add(new Vertex(w, null));
    }
    return res;
  }



  /**
   * Takes in array of Waypoints and multiplies the x and y values by a given integer s.
   * 
   * @see common.datatypes.Waypoint
   * 
   * @deprecated use {@link common.datatypes.map.MapLayer#transform(int, int, int, int)} to scale
   *
   * @param the Collection of Waypoints to be scaled.
   * @param s the integer that the x and y values of the collection will be multiplied by.
   * 
   * @return new Collection of Waypoints scaled by an Integer value.
   * 
   */
  public ArrayList<Waypoint> scale(ArrayList<Waypoint> input, int s) {
    ArrayList<Waypoint> output = new ArrayList<Waypoint>();
    for (Waypoint w : input) {
      output.add(new Waypoint(w.getX() * s, w.getY() * s));
    }
    return output;
  }

  /**
   * Converts a Region of the Map into an arrayList of Vertex's. A more flexible data Structure for
   * the GUI to pass around.
   * 
   * @see common.datatypes.map.griddedMap.Vertex
   * @see common.datatypes.map.griddedMap.Region
   *
   * @deprecated use {@link common.datatypes.map.griddedMap.GriddedMap#toArrayList()} for iterable
   *             access to Amalgamated Map data
   *
   * @param r the Region of the map.
   * 
   * @return A Collection of Vertex's from current Region r.
   * 
   */
  public ArrayList<Vertex> regionToArray(Region[][] r) {
    ArrayList<Vertex> v = new ArrayList<Vertex>();
    return v;
  }

  /**
   * Main method for running GUI.
   */
  public static void main(String[] args) {
    launch(args);
  }



  private RemoteLeader connectRMI() {
    RemoteLeader res = null;
    try {
      res = (RemoteLeader) Naming.lookup("rmi://192.168.25.42" + "/HerdLeader");//FIXME lookup IP
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotBoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // needs to be var vs hardcode
      // wait
      // send the RMI leader the herd info
    try {
      res.register(this);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return res;
  }

  @Override
  public boolean kill(String log) throws RemoteException {
    LOGGER.log(Level.SEVERE, log);
    Platform.exit();
    return true;// TODO some logic
  }

  @Override
  public void RMITest() {
    System.out.println("Member RMITest was called in the GUI");
    try {
      localLeaderRef.RMITest();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void notifyOfChange() throws RemoteException {
    try {
      localHerdData = localLeaderRef.getState();
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}

