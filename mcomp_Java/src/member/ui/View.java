package member.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class View extends Application {
    
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Pane root = new Pane();
		final Scene scene = new Scene(root ,500, 250);
		primaryStage.setScene(scene);

		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(screenBounds.getMinX());
		primaryStage.setY(screenBounds.getMinY());
		primaryStage.setWidth(screenBounds.getWidth());
		primaryStage.setHeight(screenBounds.getHeight());
		
		Rectangle r = new Rectangle();
		r.setX(screenBounds.getMinX()+10);
		r.setY(screenBounds.getMinY());
		r.setWidth(screenBounds.getWidth() / 1.5);
		r.setHeight(screenBounds.getHeight() - 50);
		r.setFill(Color.BLUE);
		r.setStroke(Color.BLACK);
		root.getChildren().add(r);
		
		Rectangle r2 = new Rectangle();
		r2.setX(screenBounds.getMaxX() -405);
		r2.setY(screenBounds.getMinY());
		r2.setWidth(screenBounds.getWidth() - r.getWidth() - 50);
		r2.setHeight(screenBounds.getHeight() - 50);
		r2.setFill(Color.RED);
		r2.setStroke(Color.BLACK);
		root.getChildren().add(r2);
		primaryStage.show();
	}


	

}
