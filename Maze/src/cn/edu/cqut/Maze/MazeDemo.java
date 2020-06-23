package cn.edu.cqut.Maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MazeDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TotalPane pane = new TotalPane();
		Scene scene = new Scene(pane, 1300, 700);

		Image ico = new Image("https://s1.ax1x.com/2020/06/23/NUSDFx.png");// Í¼±êµÄËõÂÔÍ¼
		primaryStage.getIcons().add(ico);
		primaryStage.setTitle("MazeDemo");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

}
