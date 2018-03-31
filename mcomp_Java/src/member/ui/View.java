package member.ui;

import java.util.ArrayList;
import common.datatypes.Waypoint;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
	private Group group;
	
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
		r.setWidth(screenBounds.getWidth()/1.25);
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
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		final VBox vbox = new VBox();
		vbox.setPrefWidth(200);
		vbox.setPrefHeight(screenBounds.getHeight());

		/**
		 * Button for displaying LiDar return.
		 */
		ToggleButton lidarBtn = new ToggleButton("Lidar Overlay");
		lidarBtn.setMinWidth(vbox.getPrefWidth());
		lidarBtn.setMinHeight(vbox.getPrefHeight()/6);
		toggleOffStyle(lidarBtn);
		lidarBtn.setOnAction(event ->{
			if(lidarBtn.isSelected()) {
				toggleOnStyle(lidarBtn);
				pane.getChildren().add(drawCircle());
			}else {
				toggleOffStyle(lidarBtn);
				pane.getChildren().remove(group);
			}
		});

		/**
		 * Button for displaying Map grid overlay.
		 */
		ToggleButton gridBtn = new ToggleButton("Grid Overlay");
		gridBtn.setMinWidth(vbox.getPrefWidth());
		gridBtn.setMinHeight(vbox.getPrefHeight()/6);
		toggleOffStyle(gridBtn);
		gridBtn.setOnAction(event ->{
			if(gridBtn.isSelected()) {
				toggleOnStyle(gridBtn);
			}else {
				toggleOffStyle(gridBtn);
			}
		});

		/**
		 * Button for displaying amalgamated map overlay.
		 */
		ToggleButton mapBtn = new ToggleButton("Map Overlay");
		mapBtn.setMinWidth(vbox.getPrefWidth());
		mapBtn.setMinHeight(vbox.getPrefHeight()/6);
		toggleOffStyle(mapBtn);
		mapBtn.setOnAction(event ->{
			if(mapBtn.isSelected()) {
				toggleOnStyle(mapBtn);
			}else {
				toggleOffStyle(mapBtn);
			}
		});

		/**
		 * Random Button -- TODO refine --
		 */
		ToggleButton randomBtn = new ToggleButton("Random Overlay");
		randomBtn.setMinWidth(vbox.getPrefWidth());
		randomBtn.setMinHeight(vbox.getPrefHeight()/6);
		toggleOffStyle(randomBtn);
		randomBtn.setOnAction(event ->{
			if(randomBtn.isSelected()) {
				toggleOnStyle(randomBtn);
			}else {
				toggleOffStyle(randomBtn);
			}
		});

		VBox.setMargin(gridBtn, new Insets(10));
		VBox.setMargin(mapBtn, new Insets(10));
		VBox.setMargin(randomBtn, new Insets(10));
		VBox.setMargin(lidarBtn, new Insets(10));
		vbox.setSpacing(screenBounds.getHeight()/20);
		ObservableList<Node> list = vbox.getChildren();

		list.addAll(lidarBtn, gridBtn, mapBtn, randomBtn);
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
	 * Method for taking in an ArrayList of type Waypoint.
	 * Loops through ArrayList and draws circles at waypoint(x,y).
	 * Creates a group of circles. 
	 * group displayed in map Rectangle (when lidarButton toggled on).
	 * @return group.
	 */
	public Group drawCircle() {
		ArrayList<Waypoint> lidar = new ArrayList<Waypoint>();
		group = new Group();
		for(int i = 0; i < 10; i++) {
			Waypoint waypoint = new Waypoint((i*50)+20, (i*50)+20);
			lidar.add(waypoint);
		}

		for(Waypoint w: lidar) {
			Circle circle = new Circle();
			circle.setCenterX(w.getX());
			circle.setCenterY(w.getY());
			circle.setRadius(5d);
			group.getChildren().add(circle);
		}

		return group;
	}

	/**
	 * --TODO implement-- 
	 */
	public Rectangle toggleGrid(Rectangle r) {	
		r.setStyle(" -fx-background-color: #D3D3D333,\r\n" + 
				"        linear-gradient(from 0.5px 0px to 10.5px 0px, repeat, black 5%, transparent 5%),\r\n" + 
				"        linear-gradient(from 0px 0.5px to 0px 10.5px, repeat, black 5%, transparent 5%);");
		return r;
	}

	/**
	 * Main method for running GUI. 
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

