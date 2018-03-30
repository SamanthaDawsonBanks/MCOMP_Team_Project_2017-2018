package member.ui;

import com.sun.javafx.css.Style;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class View extends Application {

	private HBox hbox;
	private StackPane mainPane;


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

		mainPane = new StackPane(hbox);
		mainPane.setPadding(new Insets(10));

		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();


	}

	public Rectangle getRect() {

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Rectangle r = new Rectangle();
		r.setX(screenBounds.getMinX());
		r.setY(screenBounds.getMinY());
		r.setWidth(screenBounds.getWidth()/1.25);
		r.setHeight(screenBounds.getHeight()-50);
		r.setFill(Color.BLUE);
		r.setStroke(Color.BLACK);
		r.setStrokeWidth(4);

		r.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.print(event.getSceneX() + "," + event.getSceneY());
				r.setFill(Color.RED);
			}
		});
		return r;
	}


	public VBox getVBox() {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		final VBox vbox = new VBox();
		vbox.setPrefWidth(200);
		vbox.setPrefHeight(screenBounds.getHeight());

		ToggleButton lidarBtn = new ToggleButton("Lidar Overlay");
		lidarBtn.setMinWidth(vbox.getPrefWidth());
		lidarBtn.setMinHeight(vbox.getPrefHeight()/6);
		toggleOffStyle(lidarBtn);
		lidarBtn.setOnAction(event ->{
			if(lidarBtn.isSelected()) {
				toggleOnStyle(lidarBtn);
			}else {
				toggleOffStyle(lidarBtn);
			}
		});
		
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
	
	public ToggleButton toggleOffStyle(ToggleButton btn) {
		btn.setStyle("-fx-background-color:#fff;"
				+"-fx-border-color: #000000;"
				+"-fx-border-width: 4px;"
				+"-fx-font-size: 20px; "
				+"-fx-font-weight: bold;");
		return btn;
	}
	
	public ToggleButton toggleOnStyle(ToggleButton btn) {
		btn.setStyle("-fx-background-color:#00cc00;"
				+"-fx-border-color: #000000;"
				+"-fx-border-width: 4px;"
				+"-fx-font-size: 20px; "
				+"-fx-font-weight: bold;");
		return btn;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
