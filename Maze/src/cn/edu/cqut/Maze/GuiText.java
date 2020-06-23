package cn.edu.cqut.Maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiText extends Application {

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BackgroundPane pane = new BackgroundPane();
		Scene scene = new Scene(pane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
