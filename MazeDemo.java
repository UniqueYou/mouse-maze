package cn.edu.cqut.Maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MazeDemo extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		TotalPane pane = new TotalPane();
		Scene scene =  new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

}
