package member.ui;

import java.util.ArrayList;
import common.datatypes.Waypoint;
import common.datatypes.map.Map;
import common.datatypes.map.MapLayer;
import common.datatypes.map.griddedMap.Vertex;
import common.datatypes.path.Path;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import pathfinding.AStar;
import unitTesting.MapTest2;


/**
 * 
 * @author Harry Jackson: 14812630.
 * GUI main class
 * Create new HBox (horizontal scene layout) 
 * getRect() method is added to HBox layout.
 * getVbox() method is added to HBox layout
 * HBox is added to current pane (allows for overlapping objects).
 * Create scene from all objects in pane.
 * Show current scene.
 */
public class View extends Application {

	private Pane pane;
	private HBox hbox;
	private int counter = 0;
	private Group circleGroup, rectangleGroup, lineGroup;
	

	@Override
	public void start(Stage primaryStage) throws Exception {

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(screenBounds.getMinX());
		primaryStage.setY(screenBounds.getMinY());
		primaryStage.setWidth(screenBounds.getWidth());
		primaryStage.setHeight(screenBounds.getHeight());

		hbox = new HBox();
		hbox.setSpacing(8);
		hbox.getChildren().addAll(getRect(), getVBox());
		pane = new Pane();
		pane.getChildren().add(hbox);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Creates a new Rectangle
	 * Set size, colour, border etc.
	 * Adds onMouseClickListener for settings destination Waypoint.
	 * Draws a circle at the destination Waypoint(x,y) with Label.  
	 * Adds the circle and label to current pane. 
	 * @returns the map Rectangle
	 * 
	 */
	public Rectangle getRect() {

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Rectangle r = new Rectangle();
		r.setX(screenBounds.getMinX());
		r.setY(screenBounds.getMinY());
		r.setWidth(screenBounds.getWidth()/1.30);
		r.setHeight(screenBounds.getHeight()-50);
		r.setFill(Color.TRANSPARENT);
		r.setStroke(Color.BLACK);
		r.setStrokeWidth(4);
		r.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(counter==0) {
					System.out.print(event.getSceneX() + "," + event.getSceneY());
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
				} 
				else {
					System.out.print(event.getSceneX() + "," + event.getSceneY());
				}
			}
		});		
		return r;
	}

	/**
	 * Method to create a VBox (vertical scene layout) with buttons.
	 * Create all buttons.
	 * Set buttons Style (CSS), size, colour etc.
	 * All buttons added to VBox (create list of buttons).
	 * Provide onClick for each button.
	 * @return VBox
	 */
	public VBox getVBox() {
		VBox vbox = new VBox();
		ObservableList<Node> list = vbox.getChildren();
		list.addAll(getVBoxMap(), getVBoxPath());
		return vbox;
	}


	/**
	 * Method to create a VBox (vertical scene layout) with buttons for map overalays.
	 * Create all buttons.
	 * Set buttons Style (CSS), size, colour etc.
	 * All buttons added to VBox (create list of buttons).
	 * Provide onClick for each button.
	 * @return VBox
	 */
	public VBox getVBoxMap() {

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		final VBox vbox = new VBox();
		final HBox hboxBtn = new HBox();
		final HBox hboxBtn2 = new HBox();

		final Label label = new Label("Map");
		label.setMaxWidth((screenBounds.getWidth() - getRect().getWidth()-10));
		label.setMinHeight(screenBounds.getHeight()/10);
		labelStyle(label);

		vbox.setPrefWidth((screenBounds.getWidth() - getRect().getWidth()-10)/2);
		vbox.setPrefHeight(screenBounds.getHeight()/2);

		/**
		 * Button for displaying LiDar return.
		 */
		ToggleButton lidarBtn = new ToggleButton("Lidar Overlay");
		lidarBtn.setMinWidth(vbox.getPrefWidth()-20);
		lidarBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(lidarBtn);
		lidarBtn.setOnAction(event ->{
			if(lidarBtn.isSelected()) {
				toggleOnStyle(lidarBtn);
				pane.getChildren().add(drawCircle(scale(MapTest2.getPresentationMaze(),40)));
			}else {
				toggleOffStyle(lidarBtn);
				pane.getChildren().remove(circleGroup);
			}
		});

		/**
		 * Button for displaying Grid overlay.
		 */
		ToggleButton gridBtn = new ToggleButton("Grid Overlay");
		gridBtn.setMinWidth(vbox.getPrefWidth()-20);
		gridBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(gridBtn);
		gridBtn.setOnAction(event ->{
			if(gridBtn.isSelected()) {
				toggleOnStyle(gridBtn);
				pane.getChildren().add(drawRectangle(scale(MapTest2.getPresentationMaze(),40)));
			}else {
				toggleOffStyle(gridBtn);
				pane.getChildren().remove(rectangleGroup);
			}
		});

		/**
		 * Button for displaying map overlay.
		 */
		ToggleButton mapBtn = new ToggleButton("Map Overlay");
		mapBtn.setMinWidth(vbox.getPrefWidth()-20);
		mapBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(mapBtn);
		mapBtn.setOnAction(event ->{
			if(mapBtn.isSelected()) {
				toggleOnStyle(mapBtn);
				pane.getChildren().add(drawGrid(scale(MapTest2.getPresentationMaze(),40)));
			}else {
				toggleOffStyle(mapBtn);
				pane.getChildren().remove(lineGroup);
			}
		});

		/**
		 * Button for displaying amalgamated map  -- TODO refine --
		 */
		ToggleButton randomBtn = new ToggleButton("Am Overlay");
		randomBtn.setMinWidth(vbox.getPrefWidth()-20);
		randomBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(randomBtn);
		randomBtn.setOnAction(event ->{
			if(randomBtn.isSelected()) {
				toggleOnStyle(randomBtn);
			}else {
				toggleOffStyle(randomBtn);
			}
		});

		vbox.setSpacing(10);

		hboxBtn.getChildren().addAll(lidarBtn, gridBtn);
		hboxBtn2.getChildren().addAll(mapBtn, randomBtn);

		hboxBtn.setSpacing(10);
		hboxBtn2.setSpacing(10);
		ObservableList<Node> list = vbox.getChildren();

		list.addAll(label, hboxBtn, hboxBtn2);
		return vbox;
	}


	/**
	 * Method to create a VBox (vertical scene layout) with buttons for displaying pathfinding.
	 * Create all buttons.
	 * Set buttons Style (CSS), size, colour etc.
	 * All buttons added to VBox (create list of buttons).
	 * Provide onClick for each button.
	 * @return VBox
	 */
	public VBox getVBoxPath() {

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		final VBox vbox = new VBox();
		final HBox hboxBtn = new HBox();
		final HBox hboxBtn2 = new HBox();

		final Label label = new Label("Pathfinding");
		label.setMaxWidth((screenBounds.getWidth() - getRect().getWidth()-10));
		label.setMinHeight(screenBounds.getHeight()/10);
		labelStyle(label);

		vbox.setPrefWidth((screenBounds.getWidth() - getRect().getWidth()-10)/2);
		vbox.setPrefHeight(screenBounds.getHeight()/2);

		/**
		 * Button for displaying Path.
		 */
		ToggleButton pathBtn = new ToggleButton("Path");
		pathBtn.setMinWidth(vbox.getPrefWidth()-20);
		pathBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(pathBtn);
		pathBtn.setOnAction(event ->{
			if(pathBtn.isSelected()) {
				toggleOnStyle(pathBtn);
			}else {
				toggleOffStyle(pathBtn);
			}
		});

		/**
		 * Button for displaying all searched nodes.
		 */
		ToggleButton searchedBtn = new ToggleButton("Searched Nodes");
		searchedBtn.setMinWidth(vbox.getPrefWidth()-20);
		searchedBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(searchedBtn);
		searchedBtn.setOnAction(event ->{
			if(searchedBtn.isSelected()) {
				toggleOnStyle(searchedBtn);
			}else {
				toggleOffStyle(searchedBtn);
			}
		});

		/**
		 * Button for displaying optimal path.
		 */
		ToggleButton optimalPathBtn = new ToggleButton("Optimal Path");
		optimalPathBtn.setMinWidth(vbox.getPrefWidth()-20);
		optimalPathBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(optimalPathBtn);
		optimalPathBtn.setOnAction(event ->{
			if(optimalPathBtn.isSelected()) {
				toggleOnStyle(optimalPathBtn);
			}else {
				toggleOffStyle(optimalPathBtn);
			}
		});

		/**
		 * Button for displaying straight line heuristic.
		 */
		ToggleButton heuristicBtn = new ToggleButton("Heuristic");
		heuristicBtn.setMinWidth(vbox.getPrefWidth()-20);
		heuristicBtn.setMinHeight(vbox.getPrefHeight()/4);
		toggleOffStyle(heuristicBtn);
		heuristicBtn.setOnAction(event ->{
			if(heuristicBtn.isSelected()) {
				toggleOnStyle(heuristicBtn);
			}else {
				toggleOffStyle(heuristicBtn);
			}
		});

		vbox.setSpacing(10);

		hboxBtn.getChildren().addAll(pathBtn, optimalPathBtn);
		hboxBtn2.getChildren().addAll(searchedBtn, heuristicBtn);
		hboxBtn.setSpacing(10);
		hboxBtn2.setSpacing(10);

		ObservableList<Node> list = vbox.getChildren();
		list.addAll(label, hboxBtn, hboxBtn2);

		return vbox;
	}

	/**
	 * Method for setting button CSS when toggled off (normal style).
	 * @return button with applied style sheet.
	 */
	public ToggleButton toggleOffStyle(ToggleButton btn) {
		btn.setStyle("-fx-background-color:#fff;"
				+"-fx-border-color: #000000;"
				+"-fx-border-width: 4px;"
				+"-fx-font-size: 20px; "
				+"-fx-font-weight: bold;");
		return btn;
	}

	/**
	 * Method for setting button CSS when toggled on.
	 * @return button with applied style sheet.
	 */
	public ToggleButton toggleOnStyle(ToggleButton btn) {
		btn.setStyle("-fx-background-color:#00cc00;"
				+"-fx-border-color: #000000;"
				+"-fx-border-width: 4px;"
				+"-fx-font-size: 20px; "
				+"-fx-font-weight: bold;");
		return btn;
	}

	/**
	 * Method for setting label CSS when toggled off (normal style).
	 * @return button with applied style sheet.
	 */
	public Label labelStyle(Label label) {
		label.setStyle("-fx-background-color:#00bfff;"
				+"-fx-border-color: #000000;"
				+"-fx-border-width: 4px;"
				+"-fx-font-size: 20px; "
				+"-fx-font-weight: bold;"
				+"-fx-alignment:center");
		return label;
	}

	/**
	 * Method for taking in an ArrayList of type Waypoint.
	 * Loops through ArrayList and draws circles at Waypoint(x,y).
	 * Creates a group of circles. 
	 * group displayed in map Rectangle (when lidarButton toggled on).
	 * @return group.
	 */
	public Group drawCircle(ArrayList<Waypoint> l) {
		circleGroup = new Group();
		for(Waypoint w: l) {
			Circle circle = new Circle();
			circle.setCenterX(w.getX());
			circle.setCenterY(w.getY());
			circle.setRadius(5d);
			circleGroup.getChildren().add(circle);
		}

		return circleGroup;
	}

	/**
	 * Method for taking in an ArrayList of type Waypoint.
	 * Draws the rectangles at blocked points on the map.
	 * @return group
	 */
	public Group drawRectangle(ArrayList<Waypoint> l) {
		rectangleGroup = new Group();
		for(Waypoint w: l) {
			Rectangle rectangle = new Rectangle();
			rectangle.setX(w.getX()-20);
			rectangle.setY(w.getY()-20);
			rectangle.setWidth(40);
			rectangle.setStroke(Color.BLACK);
			rectangle.setStrokeWidth(4);
			rectangle.setHeight(40);
			rectangle.setFill(Color.RED);
			rectangleGroup.getChildren().add(rectangle);
		}

		return rectangleGroup;
	}

	/**
	 * Method for taking in an ArrayList of type Waypoint.
	 * Draws the grid overlay.
	 * @return group
	 */
	public Group drawGrid(ArrayList<Waypoint> l) {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Rectangle r = new Rectangle();
		r.setX(screenBounds.getMinX());
		r.setY(screenBounds.getMinY());
		r.setWidth(screenBounds.getWidth()/1.3);
		r.setHeight(screenBounds.getHeight()-50);
		lineGroup = new Group();
		for(int x = 0; x < r.getWidth(); x=x+40) {
			Line line = new Line();
			line.setStartX(x-20);
			line.setEndX(x-20);
			line.setStartY(0);
			line.setEndY(r.getHeight());
			lineGroup.getChildren().add(line);
		}

		for(int y = 0; y < r.getHeight(); y=y+40) {
			Line line = new Line();
			line.setStartX(0);
			line.setEndX(r.getWidth());
			line.setStartY(y-20);
			line.setEndY(y-20);
			lineGroup.getChildren().add(line);
		}

		return lineGroup;
	}

	/**
	 * Method takes in array and scales the Waypoints
	 * 
	 */
	public ArrayList<Waypoint> scale(ArrayList<Waypoint> input, int s){
		ArrayList<Waypoint> output = new ArrayList<Waypoint>();
		for(Waypoint w: input) {
			output.add(new Waypoint(w.getX()*s, w.getY()*s));
		}
		return output;
	}
	
	/**
	 * Main method for running GUI. 
	 */
	public static void main(String[] args) {
		launch(args);
	}
}